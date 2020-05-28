package ubb.project.iss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ubb.project.iss.domain.Paper;
import ubb.project.iss.domain.PaperBid;
import ubb.project.iss.domain.PaperReview;
import ubb.project.iss.domain.UserAccount;
import ubb.project.iss.repository.PaperBidRepository;
import ubb.project. iss.repository.PaperRepository;
import ubb.project.iss.repository.PaperReviewRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.stream.Collectors;
import javax.mail.*;
import javax.mail.internet.*;

@Service
public class PaperReviewServiceImpl implements PaperReviewService {
    @Autowired
    PaperReviewRepository paperReviewRepository;
    @Autowired
    PaperBidRepository paperBidRepository;
    @Autowired
    PaperRepository paperRepository;
    @Autowired
    PaperBidService paperBidService;
    @Autowired
    UserService userService;

    @Override
    public List<PaperReview> getAll() {
        return paperReviewRepository.findAll();
    }

    @Override
    public PaperReview save(PaperReview paperReview) {
        return paperReviewRepository.save(paperReview);
    }

    @Override
    public PaperReview getById(Long id) {
        return paperReviewRepository.findById(id).get();
    }

    @Override
    public void remove(PaperReview paperReview){
        paperReviewRepository.delete(paperReview);
    }

    @Override
    public void sendDenialEmail(String to){
        String from = "our_email@gmail.com";
        String host = "localhost";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Message on your submitted paper");
            message.setText("Your paper was unfortunately rejected!");
            Transport.send(message);
        } catch (MessagingException m) {
            m.printStackTrace();
        }
    }

    @Override
    public void sendApprovalEmail(String to){
        String from = "our_email@gmail.com";
        String host = "localhost";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Message on your submitted paper");
            message.setText("Your paper was approved!");
            Transport.send(message);
        } catch (MessagingException m) {
            m.printStackTrace();
        }
    }

    @Override
    public void assignPapers(Long conferenceID) {
        List<PaperBid> paperBids = paperBidService.getAll().stream().filter(bid -> {
            Paper paper = paperRepository.findById(bid.getPaper_id()).get();
            return conferenceID.equals(paper.getConference_id());
        }).collect(Collectors.toList());
        List<Paper> papers = paperRepository.findAll().stream().filter(paper -> paper.getConference_id().equals(conferenceID)).collect(Collectors.toList());

        for(Paper paper : papers)
        {
            int currentReviewers = 0;
            List<PaperBid> bidsForThisPaper = paperBids.stream().filter(bid -> bid.getPaper_id() == paper.getId()).collect(Collectors.toList());
            List<PaperBid> forBids = bidsForThisPaper.stream().filter(bid -> bid.getBid_value() == 1).collect(Collectors.toList());
            List<PaperBid> againstBids = bidsForThisPaper.stream().filter(bid -> bid.getBid_value() == -1).collect(Collectors.toList());
            List<PaperBid> indifferentBids = bidsForThisPaper.stream().filter(bid -> bid.getBid_value() == -0).collect(Collectors.toList());
            for(int i = 0; i < Math.min(4, forBids.size()); i++)
            {
                paperReviewRepository.save(new PaperReview(forBids.get(i).getMember_id(),
                                                               forBids.get(i).getPaper_id(),
                                                               -1,
                                                               ""));
                    currentReviewers++;
            }
            if(currentReviewers < 2 && indifferentBids.size() != 0)
            {
                for(int i = 0; currentReviewers < 2 && i < indifferentBids.size(); i++)
                {
                    paperReviewRepository.save(new PaperReview(indifferentBids.get(i).getMember_id(),
                            indifferentBids.get(i).getPaper_id(),
                            -1,
                            ""));
                    currentReviewers++;
                }
            }
            if(currentReviewers < 2 && againstBids.size() != 0)
            {
                for(int i = 0; currentReviewers < 2 && i < againstBids.size(); i++)
                {
                    paperReviewRepository.save(new PaperReview(againstBids.get(i).getMember_id(),
                            againstBids.get(i).getPaper_id(),
                            -1,
                            ""));
                    currentReviewers++;
                }
            }
            if(currentReviewers < 2)
            {
                UserAccount user = userService.getById(paper.getPublisher_id());
                //denied
                this.sendDenialEmail(user.getEmail());
                paperRepository.deleteById(paper.getId());
            }
        }
    }

    //folosim asta pentru "add", deoarece am folosit save-ul ca sa asignam paper-urile la evaluatori cu imiplicit remark -1
    @Override
    @Transactional
    public void update(PaperReview entity) {
        PaperReview old = paperReviewRepository.findAll().stream().filter(paperReview -> paperReview.getMember_id() == entity.getMember_id() && paperReview.getPaper_id() == entity.getPaper_id()).collect(Collectors.toList()).get(0);
        old.setRemark(entity.getRemark());
        old.setRecommendations(entity.getRecommendations());
    }

    @Override
    public boolean checkIfApproved(Long paperID) {
        List<PaperReview> paperReviews = this.getAll();
        List<Paper> papers = paperRepository.findAll().stream().filter(paper -> paper.getId().equals(paperID)).collect(Collectors.toList());
        List<Long> paperIDs = new ArrayList<Long>();

        for (Paper paper : papers) {
            int rejects = 0;
            int approvals = 0;
            int reviewed = 0;

            for (PaperReview review : paperReviews) {
                if (review.getPaper_id() == paper.getId()) {
                    if (review.getRemark() <= 4) {
                        approvals ++;
                    }
                    else {
                        rejects ++;
                    }
                    reviewed ++;
                }
            }
            if (reviewed == 0) {
                continue;
            }
            if (rejects == 0) {
                this.sendApprovalEmail(userService.getById(paper.getPublisher_id()).getEmail());
                return true;
            }
            else if (approvals == 0){
                this.sendDenialEmail(userService.getById(paper.getPublisher_id()).getEmail());
                paperRepository.deleteById(paper.getId());
                return false;
            }
        }
        return false;
    }
}
