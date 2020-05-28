package ubb.project.iss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ubb.project.iss.domain.CommitteeMembership;
import ubb.project.iss.repository.ConferenceCommitteeRepository;

import java.util.List;

@Service
public class CommitteeMembershipServiceImpl implements CommitteeMembershipService {


    @Override
    public List<CommitteeMembership> getAll() {
        return null;
    }

    @Override
    public CommitteeMembership save(CommitteeMembership entity) {
        return null;
    }

    @Override
    public CommitteeMembership getById(Long id) {
        return null;
    }
}
