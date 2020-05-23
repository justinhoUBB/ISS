package ubb.project.iss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ubb.project.iss.domain.Conference;
import ubb.project.iss.domain.PaperBid;
import ubb.project.iss.repository.PaperBidRepository;
import ubb.project.iss.repository.PaperRepository;
import ubb.project.iss.repository.SteeringCommitteeRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class PaperBidServiceImpl implements PaperBidService {
    @Autowired
    PaperBidRepository paperBidRepository;
    @Autowired
    SteeringCommitteeRepository steeringCommitteeRepository;
    @Autowired
    PaperRepository paperRepository;
    @Autowired
    ConferenceService conferenceService;

    @Override
    public List<PaperBid> getAll() {
        return paperBidRepository.findAll();
    }

    @Override
    public PaperBid save(PaperBid paperBid, Long conferenceID) {
        LocalDate bidDate = paperBid.getBid_date();
        Conference conference = conferenceService.getById(conferenceID);
        LocalDate deadline = conference.getBid_deadline();
        if (bidDate.isBefore(deadline)) {
            return paperBidRepository.save(paperBid);
        }
        return null;
    }

    @Override
    public PaperBid getById(Long id) {
        return paperBidRepository.findById(id).get();
    }

    @Override
    public void remove(PaperBid bid) {
        paperBidRepository.delete(bid);
    }


}
