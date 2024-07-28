package com.pigeon_management_system_api.controller;

import com.pigeon_management_system_api.dto.UserRegistrationDTO;
import com.pigeon_management_system_api.model.User;
import com.pigeon_management_system_api.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        logger.info("Received request to get all users");
        List<User> users = userRepository.findAll();
        logger.info("Found " + users.size() + " users");

        return users;
    }

    @PostMapping("/register")
    public User registerUser(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        logger.info("Received request to register user with email: " + userRegistrationDTO.getEmail());


        User user = new User();
        user.setName(userRegistrationDTO.getName());
        user.setSurname(userRegistrationDTO.getSurname());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setPassword(userRegistrationDTO.getPassword());
        user.setBirthDate(userRegistrationDTO.getBirthDate());

        User savedUser = userRepository.save(user);
        logger.info("User registered with ID: " + savedUser.getId());

        return savedUser;
    }
}
