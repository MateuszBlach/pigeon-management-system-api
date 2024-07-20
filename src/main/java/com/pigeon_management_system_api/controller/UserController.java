package com.pigeon_management_system_api.controller;

import com.pigeon_management_system_api.model.User;
import com.pigeon_management_system_api.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        logger.info("Found "+users.size()+" users");

        return users;
    }
}
