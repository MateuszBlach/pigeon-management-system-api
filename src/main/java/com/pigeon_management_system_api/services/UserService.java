package com.pigeon_management_system_api.services;

import com.pigeon_management_system_api.config.AppException;
import com.pigeon_management_system_api.dto.UserLoginDTO;
import com.pigeon_management_system_api.dto.UserRegistrationDTO;
import com.pigeon_management_system_api.model.User;
import com.pigeon_management_system_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> login(UserLoginDTO userLoginDTO) {
        Optional<User> userOpt = userRepository.findByEmail(userLoginDTO.getEmail());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (userLoginDTO.getPassword().equals(user.getPassword())) {
                return Optional.of(user);
            }
            throw new AppException("Błędne hasło",HttpStatus.BAD_REQUEST);
        }

        throw new AppException("Użytkownik nie isntnieje", HttpStatus.BAD_REQUEST);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User registerNewUser(UserRegistrationDTO userRegistrationDTO){
        User user = new User();
        user.setName(userRegistrationDTO.getName());
        user.setSurname(userRegistrationDTO.getSurname());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setPassword(userRegistrationDTO.getPassword());
        user.setBirthDate(userRegistrationDTO.getBirthDate());
        return userRepository.save(user);
    }
}
