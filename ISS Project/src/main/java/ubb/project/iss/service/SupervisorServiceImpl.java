package ubb.project.iss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ubb.project.iss.domain.Supervisor;
import ubb.project.iss.repository.SupervisorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupervisorServiceImpl implements SupervisorService {
    @Autowired
    SupervisorRepository supervisorRepository;
    @Override
    public List<Supervisor> getAll() {
        return supervisorRepository.findAll();
    }

    @Override
    public List<Supervisor> getByConferenceID(Long conference_id) {
        return supervisorRepository.findAll().stream().
                filter(supervisor -> supervisor.getConference_id() == conference_id).
                collect(Collectors.toList());
    }

    @Override
    public Supervisor save(Supervisor supervisor) {
        return supervisorRepository.save(supervisor);
    }

    @Override
    @Transactional
    public Supervisor update(Supervisor supervisor) {
        Supervisor oldSupervisor = supervisorRepository.findById(supervisor.getId()).get();
        oldSupervisor.setConference_id(supervisor.getConference_id());
        oldSupervisor.setUser_id(supervisor.getUser_id());
        return oldSupervisor;
    }

    @Override
    public void delete(Long id) {
        supervisorRepository.deleteById(id);
    }

    @Override
    public Supervisor getById(Long id) {
        return supervisorRepository.findById(id).get();
    }
}
