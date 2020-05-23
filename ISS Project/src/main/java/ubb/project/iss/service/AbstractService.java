package ubb.project.iss.service;

import ubb.project.iss.domain.Abstract;

import java.util.List;

public interface AbstractService {
    List<Abstract> getAll();
    Abstract save(Abstract entity);
    Abstract getById(Long id);
}
