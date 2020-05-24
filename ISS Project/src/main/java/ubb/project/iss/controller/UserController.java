package ubb.project.iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.OptionalValueBinding;
import org.springframework.web.bind.annotation.*;
import ubb.project.iss.domain.UserAccount;
import ubb.project.iss.response.LoginForm;
import ubb.project.iss.service.UserService;
import ubb.project.iss.response.AuthenticationResponse;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge=3600,allowedHeaders = "*", allowCredentials = "true")
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

   // @RequestMapping(value = "/users/{email}", method = RequestMethod.GET)
    //Optional<UserAccount>  getByEmail(@PathVariable  String email){
      //      return userService.getByMail(email);
    //}

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AuthenticationResponse loginUser(
            @RequestBody LoginForm loginForm) {

        Optional<UserAccount> userOptional = userService.getByMail(loginForm.getEmail());

        if(userOptional.isPresent()){
            UserAccount user = userOptional.get();
            if(user.getPassword().equals( loginForm.getPassword() ) ) {

                return AuthenticationResponse.builder()
                        .status("Success")
                        .userAccount(null)
                        .message("User found!")
                        .role(null)
                        .isError(false)
                        .build();
            }
        }
        return AuthenticationResponse.builder()
                .status("error")
                .message("No registered user with this username and password")
                .role("")
                .userAccount(null)
                .isError(true)
                .build();
    }

    @RequestMapping(value = "/users/{email}", method = RequestMethod.GET)
    public AuthenticationResponse logAndGetUser(
            @PathVariable String email) {


        Optional<UserAccount> user = userService.getByMail(email);

        if(user.isPresent()){

            return AuthenticationResponse.builder()
                    .status("Success")
                    .role(null)
                    .centerId(0)
                    .message("The user is now logged")
                    .isError(false)
                    .userAccount(user.orElse(null))
                    .build();
        }

        return AuthenticationResponse.builder()
                .status("failure")
                .role("")
                .userAccount(null)
                .message("The user doesn't exist")
                .isError(true)
                .build();

    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public AuthenticationResponse logoutUser(
            @RequestBody LoginForm loginForm) {

        System.out.println("A POST request was made on /logout");

        Optional<UserAccount> user = userService.getByMail(loginForm.getEmail());

        if(user.isPresent()){


            return AuthenticationResponse.builder()
                    .status("Success")
                    .message("The user is now logged out")
                    .isError(false)
                    .build();
        }

        return AuthenticationResponse.builder()
                .status("failure")
                .role("")
                .userAccount(null)
                .message("The user doesn't exist")
                .isError(true)
                .build();

    }


}