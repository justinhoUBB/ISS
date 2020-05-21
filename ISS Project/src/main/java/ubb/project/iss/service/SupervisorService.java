package ubb.project.iss.service;

import net.bytebuddy.implementation.bind.annotation.Super;
import ubb.project.iss.domain.Paper;
import ubb.project.iss.domain.Supervisor;

import java.util.List;

public interface SupervisorService {
    List<Supervisor> getAll();
    List<Supervisor> getByConferenceID(Long conference_id);
    Supervisor save(Supervisor supervisor);
    Supervisor update(Supervisor supervisor);
    void delete(Long id);
    Supervisor getById(Long id);
}
