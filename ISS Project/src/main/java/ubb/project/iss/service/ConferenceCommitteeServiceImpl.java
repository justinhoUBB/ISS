package ubb.project.iss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ubb.project.iss.domain.CommitteeMembership;
import ubb.project.iss.domain.ConferenceCommittee;
import ubb.project.iss.repository.ConferenceCommitteeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConferenceCommitteeServiceImpl implements ConferenceCommitteeService {

    @Autowired
    ConferenceCommitteeRepository conferenceCommitteeRepository;

    @Override
    public List<ConferenceCommittee> getAll() {
        return conferenceCommitteeRepository.findAll();
    }

    @Override
    public ConferenceCommittee save(ConferenceCommittee entity) {
        return conferenceCommitteeRepository.save(entity);
    }

    @Override
    public ConferenceCommittee getById(Long id) {
        return conferenceCommitteeRepository.findById(id).orElse(null);
    }

    @Override
    public List<ConferenceCommittee> getUserById(Long conference_id,ConferenceCommittee conferenceCommittee) {
        return conferenceCommitteeRepository.findAll().stream().filter(n -> n.getConference_id() == conference_id && conferenceCommittee.getUser_id() == n.getUser_id()).collect(Collectors.toList());
    }
}
