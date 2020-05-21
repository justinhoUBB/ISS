package ubb.project.iss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ubb.project.iss.domain.Conference;
import ubb.project.iss.domain.Paper;
import ubb.project.iss.domain.SteeringCommittee;
import ubb.project.iss.repository.ConferenceRepository;

import java.util.List;

public interface ConferenceService {
    List<Conference> getAll();
    Conference save(Conference entity);
    Conference getById(Long id);
}
