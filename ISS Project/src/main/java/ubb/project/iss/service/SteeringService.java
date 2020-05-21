package ubb.project.iss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ubb.project.iss.domain.Paper;
import ubb.project.iss.domain.SteeringCommittee;
import ubb.project.iss.domain.User;
import ubb.project.iss.repository.SteeringCommitteeRepository;

import java.util.List;
public interface SteeringService {
    List<SteeringCommittee> getAll();
    SteeringCommittee save(SteeringCommittee entity);
    SteeringCommittee getById(Long id);
}
