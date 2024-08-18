package com.pigeon_management_system_api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "\"user\"")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
    @SequenceGenerator(name = "user_seq_gen", sequenceName = "user_seq", allocationSize = 1)
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String password;
    @Column(name = "birth_date")
    private Date birthDate;
    private String token;
}
