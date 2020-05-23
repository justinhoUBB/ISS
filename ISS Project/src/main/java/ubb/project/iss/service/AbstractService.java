package ubb.project.iss.service;

import ubb.project.iss.domain.AbstractTable;

import java.util.List;

public interface AbstractService {
    List<AbstractTable> getAll();
    AbstractTable save(AbstractTable entity);
    AbstractTable getById(Long id);
}
