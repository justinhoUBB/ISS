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
    AuthenticationResponse save(@RequestBody UserAccount user ) {

        Optional<UserAccount> userOptional = Optional.ofNullable(userService.save(user));

        return AuthenticationResponse.builder()
                    .status("failure")
                    .userId(user.getId())
                    .role(user.is_committee_member())
                    .build();


    }
//    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    //   UserAccount getById(@PathVariable Long id) {
    //       return userService.getById(id);
    //   }

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
                        .userId(user.getId())
                        .role(user.is_committee_member())
                        .isError(false)
                        .build();
            }
        }
        return AuthenticationResponse.builder()
                .status("error")
                .userId(0)
                .role(false)
                .userAccount(null)
                .isError(true)
                .build();
    }

    @RequestMapping(value = "/users/{email}", method = RequestMethod.GET)
    public AuthenticationResponse logAndGetUser(
            @PathVariable String email) {


        Optional<UserAccount> user = userService.getByMail(email);

        if(user.isPresent()){
            UserAccount user2 = user.get();
            return AuthenticationResponse.builder()
                    .status("Success")
                    .role(user2.is_committee_member())
                    .centerId(user2.getId())
                    .userId(user2.getId())
                    .isError(false)
                    .userAccount(user.orElse(null))
                    .build();
        }

        return AuthenticationResponse.builder()
                .status("failure")
                .role(false)
                .userAccount(null)
                .userId(0)
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
                    .userId(0)
                    .isError(false)
                    .build();
        }

        return AuthenticationResponse.builder()
                .status("failure")
                .role(false)
                .userAccount(null)
                .userId(0)
                .isError(true)
                .build();

    }


}