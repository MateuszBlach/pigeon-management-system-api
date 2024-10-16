package com.pigeon_management_system_api.controller;


import com.pigeon_management_system_api.dto.FlightDTO;
import com.pigeon_management_system_api.services.FlightService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {

    private static final Logger logger = LoggerFactory.getLogger(FlightController.class);

    @Autowired
    private FlightService flightService;

    @GetMapping("/{flight_id}/all")
    public ResponseEntity<List<FlightDTO>> getAllFlights(
            @PathVariable("flight_id") Integer flightId
    ) {
        logger.info("Received request to get all flights for flightId: "+flightId);
        List<FlightDTO> flightDTOList =flightService.getAllFlightsForUserId(flightId);
        logger.info("Found "+flightDTOList.size()+" flights");
        return ResponseEntity.ok(flightDTOList);
    }

    @PostMapping("/add")
    public ResponseEntity<FlightDTO> addFlight(@Valid @RequestBody FlightDTO flightDTO) {
        logger.info("Receiver request to add flight: "+flightDTO.toString());
        FlightDTO flight = flightService.addFlight(flightDTO);
        return ResponseEntity.ok(flight);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<FlightDTO> deleteFlight(@Valid @RequestBody FlightDTO flightDTO) {
        logger.info("Receiver request to delete flight: "+flightDTO.toString());
        flightService.deleteFlight(flightDTO);
        return ResponseEntity.ok(flightDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<FlightDTO> updateFlight(
            @Valid @RequestBody FlightDTO flightDTO
    ){
        logger.info("Received request to update flight: "+flightDTO.toString());
        FlightDTO flight = flightService.updateFlight(flightDTO);
        return ResponseEntity.ok(flight);
    }


}
