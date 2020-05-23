package ubb.project.iss.service;

import ubb.project.iss.domain.UserTable;

import java.util.List;
public interface UserService {
    List<UserTable> getAll();
    UserTable save(UserTable entity);
    UserTable getById(Long id);
}
