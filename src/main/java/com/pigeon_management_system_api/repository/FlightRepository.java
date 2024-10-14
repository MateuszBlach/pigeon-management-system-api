package com.pigeon_management_system_api.repository;

import com.pigeon_management_system_api.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, String> {
}