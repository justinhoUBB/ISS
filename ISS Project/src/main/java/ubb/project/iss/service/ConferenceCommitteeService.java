package ubb.project.iss.service;

import ubb.project.iss.domain.CommitteeMembership;
import ubb.project.iss.domain.ConferenceCommittee;

import java.util.List;

public interface ConferenceCommitteeService {

    List<ConferenceCommittee> getAll();
    ConferenceCommittee save(ConferenceCommittee entity);
    ConferenceCommittee getById(Long id);
    List<ConferenceCommittee> getUserById(Long id,ConferenceCommittee conferenceCommittee);
}
