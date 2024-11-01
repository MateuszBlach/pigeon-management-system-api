package com.pigeon_management_system_api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "pigeon")
public class Pigeon implements Serializable {

    @Id
    private String ring;
    private String gender;
    private String color;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "father_ring")
    private String fatherRing;
    @Column(name = "mother_ring")
    private String motherRing;
    private String description;
}
