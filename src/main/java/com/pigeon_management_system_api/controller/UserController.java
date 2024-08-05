package com.pigeon_management_system_api.controller;

import com.pigeon_management_system_api.dto.UserLoginDTO;
import com.pigeon_management_system_api.dto.UserRegistrationDTO;
import com.pigeon_management_system_api.model.User;
import com.pigeon_management_system_api.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        logger.info("Received request to get all users");
        List<User> users = userService.getAllUsers();
        logger.info("Found " + users.size() + " users");

        return ResponseEntity.ok(users);
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        logger.info("Received request to register user with email: " + userRegistrationDTO.getEmail());
        User registredUser = userService.registerNewUser(userRegistrationDTO);
        logger.info("User registered with ID: " + registredUser.getId());
        return ResponseEntity.ok(registredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        logger.info("Received request to login user with email: " + userLoginDTO.getEmail());

        Optional<User> user = userService.login(userLoginDTO);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}
