package iss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import iss.domain.SteeringCommittee;
import iss.repository.SteeringCommitteeRepository;

import java.util.List;
@Service
public class SteeringServiceImpl implements SteeringService {
    @Autowired
    private SteeringCommitteeRepository steeringCommitteeRepository;
    @Override
    public List<SteeringCommittee> getAll() {
        List<SteeringCommittee> result = steeringCommitteeRepository.findAll();
        return result;
    }

    @Override
    public SteeringCommittee save(SteeringCommittee entity) {
        return steeringCommitteeRepository.save(entity);
    }

    @Override
    public SteeringCommittee getById(Long id) {
        SteeringCommittee update = steeringCommitteeRepository.findById(id).orElse(new SteeringCommittee());
        return update;
    }
}