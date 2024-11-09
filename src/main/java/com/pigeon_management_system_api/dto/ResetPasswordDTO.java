package com.pigeon_management_system_api.dto;

import lombok.Data;

@Data
public class ResetPasswordDTO {
    private Integer token;
    private String newPassword;
}
