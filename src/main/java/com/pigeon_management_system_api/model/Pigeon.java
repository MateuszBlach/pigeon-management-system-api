package com.pigeon_management_system_api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "pigeon")
public class Pigeon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pigeon_seq_gen")
    @SequenceGenerator(name = "pigeon_seq_gen", sequenceName = "pigeon_seq", allocationSize = 1)
    private Integer id;
    private String ring;
    private String gender;
    private String color;
    @Column(name = "user_id")
    private Integer userId;
}
