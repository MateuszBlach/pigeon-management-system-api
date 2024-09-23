package com.pigeon_management_system_api.dto;

import java.util.Date;

public record UserRegistrationDTO (String name, String surname, char[] password, String email, Date birthDate){

}
