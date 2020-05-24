package ubb.project.iss.service;

import ubb.project.iss.domain.UserAccount;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserAccount> getAll();
    UserAccount save(UserAccount entity);
    UserAccount getById(Long id);
    Optional<UserAccount> getByMail(String mail);
}
