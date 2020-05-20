package ubb.project.iss.service;

import java.util.List;

public interface ServiceInterface<T> {
    List<T> getAll();
    T save(T entity);
    T getById(Long id);
}
