package com.pigeon_management_system_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class PigeonResultDTO {

    private Double flightRecordDistance;
    private Double coefic;
    private Double points;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date flightDate;
    private String city;

}
