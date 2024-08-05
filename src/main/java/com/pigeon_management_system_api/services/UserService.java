package com.pigeon_management_system_api.services;

import com.pigeon_management_system_api.dto.UserLoginDTO;
import com.pigeon_management_system_api.model.User;
import com.pigeon_management_system_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        }

        return Optional.empty();
    }
}
