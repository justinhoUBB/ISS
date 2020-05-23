package iss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import iss.domain.UserAccount;
import iss.repository.UserRepository;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<UserAccount> getAll() {
        List<UserAccount> result = userRepository.findAll();
        return result;
    }

    @Override
    public UserAccount save(UserAccount entity) {
        return userRepository.save(entity);
    }

    @Override
    public UserAccount getById(Long id) {
        UserAccount update = userRepository.findById(id).orElse(new UserAccount());
        return update;
    }
}