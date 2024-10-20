package com.pigeon_management_system_api.repository;

import com.pigeon_management_system_api.model.FlightRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlightRecordRepository extends JpaRepository<FlightRecord, Integer> {

    List<FlightRecord> findByFlightId(Integer flightId);

    @Query("SELECT fr, f FROM FlightRecord fr JOIN Flight f ON fr.flightId = f.id WHERE fr.pigeonRing = :pigeonRing")
    List<Object[]> findByPigeonRingWithFlightInfo(@Param("pigeonRing") String pigeonRing);
}
