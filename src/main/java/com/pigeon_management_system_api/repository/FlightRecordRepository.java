package com.pigeon_management_system_api.repository;

import com.pigeon_management_system_api.model.FlightRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRecordRepository extends JpaRepository<FlightRecord, Integer> {

    List<FlightRecord> findByFlightId(Integer flightId);

    List<FlightRecord> findByPigeonRing(String ring);
}
