package com.pigeon_management_system_api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "flight")
public class Flight implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flight_seq_gen")
    @SequenceGenerator(name = "flight_seq_gen", sequenceName = "flight_seq", allocationSize = 1)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    private Double distance;
    private Date date;
    private String city;
    private String weather;
    @Column(name = "wind_direction")
    private String windDirection;
    @Column(name = "wind_speed")
    private Double windSpeed;

}
