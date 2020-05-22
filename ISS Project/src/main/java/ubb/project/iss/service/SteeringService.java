package ubb.project.iss.service;

import ubb.project.iss.domain.SteeringCommittee;

import java.util.List;
public interface SteeringService {
    List<SteeringCommittee> getAll();
    SteeringCommittee save(SteeringCommittee entity);
    SteeringCommittee getById(Long id);
}
