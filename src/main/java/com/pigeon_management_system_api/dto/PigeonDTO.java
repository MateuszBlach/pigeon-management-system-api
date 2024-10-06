package com.pigeon_management_system_api.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PigeonDTO {

    @NotEmpty
    private Integer id;
    @NotEmpty
    private String ring;
    private String gender;
    private String eyeColor;
    private String plumageColor;
    @NotEmpty
    private Integer userId;
}
