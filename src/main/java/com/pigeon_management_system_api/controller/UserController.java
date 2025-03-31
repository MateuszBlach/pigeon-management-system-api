package com.pigeon_management_system_api.controller;

import com.pigeon_management_system_api.config.UserAuthenticationProvider;
import com.pigeon_management_system_api.dto.ResetPasswordDTO;
import com.pigeon_management_system_api.dto.UserDTO;
import com.pigeon_management_system_api.dto.UserLoginDTO;
import com.pigeon_management_system_api.dto.UserRegistrationDTO;
import com.pigeon_management_system_api.model.User;
import com.pigeon_management_system_api.services.PasswordResetTokenService;
import com.pigeon_management_system_api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    private final UserService userService;
    private final PasswordResetTokenService passwordResetTokenService;
    private final UserAuthenticationProvider userAuthenticationProvider;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        logger.info("Received request to get all users");
        List<User> users = userService.getAllUsers();
        logger.info("Found " + users.size() + " users");

        return ResponseEntity.ok(users);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        logger.info("Received request to register user with email: " + userRegistrationDTO.email());
        UserDTO registredUser = userService.registerNewUser(userRegistrationDTO);
        registredUser.setToken(userAuthenticationProvider.createToken(registredUser));
        return ResponseEntity.ok(registredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        logger.info("Received request to login user with email: " + userLoginDTO.email());

        UserDTO userDto = userService.login(userLoginDTO);
        userDto.setToken(userAuthenticationProvider.createToken(userDto));
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/request-reset-password/{email}")
    public ResponseEntity requestResetPassword(
            @PathVariable("email") String email
    ) {

        Integer token = passwordResetTokenService.generateResetToken(email);
        passwordResetTokenService.sendResetEmail(email,token);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Boolean> resetPassword(
            @Valid @RequestBody ResetPasswordDTO resetPasswordDTO
    ) {
        Boolean isPasswordChanged = passwordResetTokenService.resetPassword(resetPasswordDTO);
        return ResponseEntity.ok(isPasswordChanged);
    }
}
