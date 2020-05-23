package iss.service;

import iss.domain.SteeringCommittee;

import java.util.List;
public interface SteeringService {
    List<SteeringCommittee> getAll();
    SteeringCommittee save(SteeringCommittee entity);
    SteeringCommittee getById(Long id);
}
