package service;

import domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceClient implements UserService{

    @Autowired
    private UserService userService;

    @Override
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
