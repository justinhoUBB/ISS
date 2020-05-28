package ubb.project.iss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ubb.project.iss.domain.Conference;
import ubb.project.iss.domain.Supervisor;
import ubb.project.iss.repository.ConferenceRepository;

import java.time.LocalDate;
import java.util.List;
@Service
public class ConferenceServiceImpl implements ConferenceService {
    @Autowired
    private ConferenceRepository conferenceRepository;
    @Override
    public List<Conference> getAll() {
        List<Conference> result = conferenceRepository.findAll();
        return result;
    }

    @Override
    public Conference save(Conference entity) {
        return conferenceRepository.save(entity);
    }

    @Override
    public Conference getById(Long id) {
        Conference update = conferenceRepository.findById(id).orElse(null);
        return update;
    }

    @Override
    public LocalDate getBidById(Long id) {
        Conference update = conferenceRepository.findById(id).orElse(null);
        return update.getBid_deadline();
    }



    @Override
    @Transactional
    public Conference update(Conference conference, Long id) {
        Conference oldConference = conferenceRepository.findById(id).get();
        oldConference.setPaper_deadline(conference.getPaper_deadline());
        oldConference.setBid_deadline(conference.getBid_deadline());
        oldConference.setStarting_date(conference.getStarting_date());
        return oldConference;
    }

    @Override
    @Transactional
    public void settleBids(Long id) {
        Conference oldConference = conferenceRepository.findById(id).get();
        oldConference.setWere_bids_settled(true);
    }
}