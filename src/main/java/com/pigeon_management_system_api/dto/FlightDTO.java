package com.pigeon_management_system_api.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class FlightDTO {

    @NotEmpty
    private Integer id;
    @NotEmpty
    private Integer userId;
    @NotEmpty
    private Double distance;
    @NotEmpty
    private Date date;
    @NotEmpty
    private String city;
    private String weather;
    private String windDirection;
    private Double windSpeed;
}
