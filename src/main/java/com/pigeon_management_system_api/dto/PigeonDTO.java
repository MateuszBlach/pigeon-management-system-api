package com.pigeon_management_system_api.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PigeonDTO {

    @NotEmpty
    private String ring;
    private String gender;
    private String color;
    @NotEmpty
    private Integer userId;
    private String fatherRing;
    private String motherRing;
    private String description;
}
