package ubb.project.iss.service;

import ubb.project.iss.domain.Conference;
import ubb.project.iss.domain.Supervisor;

import java.time.LocalDate;
import java.util.List;

public interface ConferenceService {
    List<Conference> getAll();
    Conference save(Conference entity);
    Conference getById(Long id);
    Conference update(Conference conference,Long id);
    LocalDate getBidById(Long id);
}
