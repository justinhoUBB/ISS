package service;

import domain.User;
import repository.UserRepository;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository repo){
        userRepository = repo;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
}
