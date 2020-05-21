package ubb.project.iss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ubb.project.iss.domain.Paper;
import ubb.project.iss.domain.SteeringCommittee;
import ubb.project.iss.domain.User;
import ubb.project.iss.repository.SteeringCommitteeRepository;

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
