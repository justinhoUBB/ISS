package ubb.project.iss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ubb.project.iss.domain.Conference;
import ubb.project.iss.domain.Paper;
import ubb.project.iss.domain.User;
import ubb.project.iss.repository.PaperRepository;
import ubb.project.iss.repository.UserRepository;

import java.util.List;
@Service
public class UserServiceImpl implements ServiceInterface<User> {
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        return result;
    }

    @Override
    public User save(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public User getById(Long id) {
        User update = userRepository.findById(id).orElse(new User());
        return update;
    }
}
