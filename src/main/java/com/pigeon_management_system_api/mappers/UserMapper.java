package com.pigeon_management_system_api.mappers;

import com.pigeon_management_system_api.dto.UserRegistrationDTO;
import com.pigeon_management_system_api.model.User;
import com.pigeon_management_system_api.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(User user);

    @Mapping(target = "password", ignore = true)
    User registrationToUser(UserRegistrationDTO registrationDTO);
}
