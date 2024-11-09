package com.pigeon_management_system_api.repository;

import com.pigeon_management_system_api.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {
    PasswordResetToken findByToken(Integer token);
}
