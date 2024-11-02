package com.pigeon_management_system_api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "flight_record")
public class FlightRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flight_record_seq_gen")
    @SequenceGenerator(name = "flight_record_seq_gen", sequenceName = "flight_record_seq", allocationSize = 1)
    private Integer id;
    @Column(name = "flight_id")
    private Integer flightId;
    @Column(name = "pigeon_ring")
    private String pigeonRing;
    private Double position;
    private Double coefic;
    private Double points;
}
