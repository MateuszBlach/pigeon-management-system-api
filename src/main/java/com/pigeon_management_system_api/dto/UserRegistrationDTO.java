package com.pigeon_management_system_api.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class UserRegistrationDTO {


    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "Surname is required")
    private String surname;

    @NotEmpty(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    private Date birthDate;
}
