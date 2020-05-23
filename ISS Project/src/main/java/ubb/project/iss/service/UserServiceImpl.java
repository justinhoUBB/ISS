package ubb.project.iss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ubb.project.iss.domain.UserTable;
import ubb.project.iss.repository.UserRepository;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<UserTable> getAll() {
        List<UserTable> result = userRepository.findAll();
        return result;
    }

    @Override
    public UserTable save(UserTable entity) {
        return userRepository.save(entity);
    }

    @Override
    public UserTable getById(Long id) {
        UserTable update = userRepository.findById(id).orElse(new UserTable());
        return update;
    }
}