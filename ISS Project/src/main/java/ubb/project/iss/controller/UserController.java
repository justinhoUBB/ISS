package iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import iss.domain.UserAccount;
import iss.service.UserService;

import java.util.List;
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    List<UserAccount> getUsers() {
        return userService.getAll();
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    UserAccount save(@RequestBody UserAccount user) {
        return userService.save(user);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    UserAccount getById(@PathVariable Long id) {
        return userService.getById(id);
    }
}