package iss.service;

import iss.domain.UserAccount;

import java.util.List;
public interface UserService {
    List<UserAccount> getAll();
    UserAccount save(UserAccount entity);
    UserAccount getById(Long id);
}
