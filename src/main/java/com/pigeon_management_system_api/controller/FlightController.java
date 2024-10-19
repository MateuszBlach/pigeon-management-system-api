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

    @GetMapping("/{user_id}/all")
    public ResponseEntity<List<FlightDTO>> getAllFlights(
            @PathVariable("user_id") Integer userId
    ) {
        logger.info("Received request to get all flights for userId: "+userId);
        List<FlightDTO> flightDTOList =flightService.getAllFlightsForUserId(userId);
        logger.info("Found "+flightDTOList.size()+" flights");
        return ResponseEntity.ok(flightDTOList);
    }

    @PostMapping("/add")
    public ResponseEntity<FlightDTO> addFlight(@Valid @RequestBody FlightDTO flightDTO) {
        logger.info("Receiver request to add flight: "+flightDTO.toString());
        FlightDTO flight = flightService.addFlight(flightDTO);
        return ResponseEntity.ok(flight);
    }

    @DeleteMapping("/delete/{flight_id}")
    public ResponseEntity deleteFlight(
            @PathVariable("flight_id") Integer flightId
    ) {
        logger.info("Receiver request to delete flight of id: "+flightId);
        flightService.deleteFlight(flightId);
        return ResponseEntity.noContent().build();
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
