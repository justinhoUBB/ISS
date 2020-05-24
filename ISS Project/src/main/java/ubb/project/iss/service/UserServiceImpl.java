package ubb.project.iss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ubb.project.iss.domain.UserAccount;
import ubb.project.iss.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public UserAccount getById(@PathVariable Long id) {
        UserAccount update = userRepository.findById(id).orElse(new UserAccount());
        return update;
    }

    @Override
    public Optional<UserAccount> getByMail(String email) {
        List<UserAccount> result = userRepository.findAll().stream().filter(n -> n.getEmail().equals(email)).collect(Collectors.toList());
        if(result.isEmpty())
            return Optional.empty();

        return Optional.ofNullable(result.get(0));
    }

}