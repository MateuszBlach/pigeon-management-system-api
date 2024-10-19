package com.pigeon_management_system_api.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class FlightRecordDTO {

    @NotEmpty
    private Integer id;
    @NotEmpty
    private Integer flightId;
    @NotEmpty
    private String pigeonRing;
    @NotEmpty
    private Double distance;
    @NotEmpty
    private Double coefic;
    @NotEmpty
    private Double points;

}
