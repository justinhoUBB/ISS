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
public interface UserService {
    List<User> getAll();
    User save(User entity);
    User getById(Long id);
}
