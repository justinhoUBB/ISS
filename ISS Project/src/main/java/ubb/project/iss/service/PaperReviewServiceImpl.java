package ubb.project.iss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public void assignPapers() {
        List<PaperBid> paperBids = paperBidService.getAll();
        List<Paper> papers = paperRepository.findAll();
        List<Long> paperIDs = new ArrayList<Long>();
        List<Long> bidIDs = new ArrayList<Long>();

        for (long i = 1; i <= papers.size(); i++) {
            paperIDs.add(i);
        }

        for (long i = 1; i <= paperBids.size(); i++) {
            bidIDs.add(i);
        }

        for (Paper paper : papers) {
            int numberOfReviewers = 0;
            List<Long> willingReviewersIDs = new ArrayList<Long>();
            List<Long> mehReviewersIDs = new ArrayList<Long>();
            List<Long> candidates = new ArrayList<Long>();

            for (Long id : bidIDs) {
                PaperBid bid = paperBidService.getById(id);
                if (bid.getPaper_id() == paper.getId() && bid.getBid_value() == 1) {
                    willingReviewersIDs.add(bid.getMember_id());
                }
                else if (bid.getPaper_id() == paper.getId() && bid.getBid_value() == 0) {
                    mehReviewersIDs.add(bid.getMember_id());
                }
            }

            while (willingReviewersIDs.size() > 0 && numberOfReviewers < 2){
                Random rand = new Random();
                Long reviewerID = willingReviewersIDs.get(rand.nextInt(willingReviewersIDs.size()));
                willingReviewersIDs.remove(reviewerID);
                numberOfReviewers ++;
                candidates.add(reviewerID);
            }

            while (mehReviewersIDs.size() > 0 && numberOfReviewers < 2){
                Random rand = new Random();
                Long reviewerID = mehReviewersIDs.get(rand.nextInt(mehReviewersIDs.size()));
                mehReviewersIDs.remove(reviewerID);
                numberOfReviewers ++;
                candidates.add(reviewerID);
            }

            if (numberOfReviewers == 2) {
                PaperReview paperReview1 = new PaperReview(candidates.get(0), paper.getId(), -1, "");
                PaperReview paperReview2 = new PaperReview(candidates.get(1), paper.getId(), -1, "");
                this.save(paperReview1);
                this.save(paperReview2);
            }
            else {
                UserAccount user = userService.getById(paper.getPublisher_id());
                //denied
                this.sendDenialEmail(user.getEmail());
            }
        }
    }

    //folosim asta pentru "add", deoarece am folosit save-ul ca sa asignam paper-urile la evaluatori cu imiplicit remark -1
    @Override
    public void update(Long paperReviewID, Integer newRemark, String newRecommendation) {
        this.getById(paperReviewID).setRemark(newRemark);
        this.getById(paperReviewID).setRecommendations(newRecommendation);
    }

    public boolean checkIfApproved() {
        List<PaperReview> paperReviews = this.getAll();
        List<Paper> papers = paperRepository.findAll();
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
                return false;
            }
        }
        return false;
    }
}
