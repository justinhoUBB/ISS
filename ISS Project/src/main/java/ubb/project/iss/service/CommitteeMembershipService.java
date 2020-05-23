package ubb.project.iss.service;

import ubb.project.iss.domain.CommitteeMembership;

import java.util.List;

public interface CommitteeMembershipService {
    List<CommitteeMembership> getAll();
    CommitteeMembership save(CommitteeMembership entity);
    CommitteeMembership getById(Long id);
}
