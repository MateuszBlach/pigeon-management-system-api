package com.pigeon_management_system_api.services;

import com.pigeon_management_system_api.config.AppException;
import com.pigeon_management_system_api.dto.UserDTO;
import com.pigeon_management_system_api.dto.UserLoginDTO;
import com.pigeon_management_system_api.dto.UserRegistrationDTO;
import com.pigeon_management_system_api.mappers.UserMapper;
import com.pigeon_management_system_api.model.User;
import com.pigeon_management_system_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDTO login(UserLoginDTO userLoginDTO) {
        User user = userRepository.findByEmail(userLoginDTO.email())
                .orElseThrow(() -> new AppException("Błędny email", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(userLoginDTO.password()), user.getPassword())) {
            return userMapper.toUserDTO(user);
        }
        throw new AppException("Błędne hasło", HttpStatus.BAD_REQUEST);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserDTO registerNewUser(UserRegistrationDTO userRegistrationDTO){
        Optional<User> optionalUser = userRepository.findByEmail(userRegistrationDTO.email());

        if (optionalUser.isPresent()) {
            throw new AppException("Email już istnieje", HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.registrationToUser(userRegistrationDTO);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userRegistrationDTO.password())));

        User savedUser = userRepository.save(user);

        return userMapper.toUserDTO(savedUser);
    }
}
