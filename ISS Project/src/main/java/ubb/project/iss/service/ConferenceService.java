package iss.service;

import iss.domain.Conference;

import java.util.List;

public interface ConferenceService {
    List<Conference> getAll();
    Conference save(Conference entity);
    Conference getById(Long id);
}
