package com.pigeon_management_system_api.model;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "password_reset_token")
public class PasswordResetToken implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "password_reset_token_seq_gen")
    @SequenceGenerator(name = "password_reset_token_seq_gen", sequenceName = "password_reset_token_seq", allocationSize = 1)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    private Integer token;
    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;
}
