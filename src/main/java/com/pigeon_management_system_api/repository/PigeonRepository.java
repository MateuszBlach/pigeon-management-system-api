package com.pigeon_management_system_api.repository;

import com.pigeon_management_system_api.model.Pigeon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PigeonRepository extends JpaRepository<Pigeon, String> {
    List<Pigeon> findByUserId(Integer userId);
}
