package iss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import iss.domain.Conference;
import iss.repository.ConferenceRepository;

import java.util.List;
@Service
public class ConferenceServiceImpl implements ConferenceService {
    @Autowired
    private ConferenceRepository conferenceRepository;
    @Override
    public List<Conference> getAll() {
        List<Conference> result = conferenceRepository.findAll();
        return result;
    }

    @Override
    public Conference save(Conference entity) {
        return conferenceRepository.save(entity);
    }

    @Override
    public Conference getById(Long id) {
        Conference update = conferenceRepository.findById(id).orElse(null);
        return update;
    }
}