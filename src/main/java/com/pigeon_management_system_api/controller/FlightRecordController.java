package com.pigeon_management_system_api.controller;

import com.pigeon_management_system_api.dto.FlightRecordDTO;
import com.pigeon_management_system_api.services.FlightRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/flight-record")
public class FlightRecordController {

    private static final Logger logger = LoggerFactory.getLogger(FlightRecordController.class);

    @Autowired
    private FlightRecordService flightRecordService;

    @GetMapping("/{flight_id}/all")
    public ResponseEntity<List<FlightRecordDTO>> getAllFlightRecordsForFlight(@PathVariable("flight_id") Integer flightId) {
        logger.info("Received request to get all flight records for flight id: "+flightId);
        List<FlightRecordDTO> flightRecordList = flightRecordService.getAllFlightRecordsForFlight(flightId);
        logger.info("Found "+flightRecordList.size()+" flight records for flight id: "+flightId);
        return ResponseEntity.ok(flightRecordList);

    }

    @PostMapping("/add")
    public ResponseEntity<FlightRecordDTO> addFlightRecord(
            @Valid @RequestBody FlightRecordDTO flightRecordDTO
    ) {
        FlightRecordDTO flightRecord = flightRecordService.addFlightRecord(flightRecordDTO);
        return ResponseEntity.ok(flightRecord);
    }

    @DeleteMapping("/delete/{flight_reord_id}")
    public ResponseEntity deleteFlightRecord(
            @PathVariable("flight_reord_id") Integer flightRecordId
    ) {
        flightRecordService.deleteFlightRecord(flightRecordId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<FlightRecordDTO> updateFlightRecord(
            @Valid @RequestBody FlightRecordDTO flightRecordDTO
    ) {
        FlightRecordDTO flightRecord = flightRecordService.updateFlightRecord(flightRecordDTO);
        return ResponseEntity.ok(flightRecord);
    }

}
