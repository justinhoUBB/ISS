package iss.service;

import iss.domain.CommitteeMembership;

import java.util.List;

public interface CommitteeMembershipService {
    List<CommitteeMembership> getAll();
    CommitteeMembership save(CommitteeMembership entity);
    CommitteeMembership getById(Long id);
}
