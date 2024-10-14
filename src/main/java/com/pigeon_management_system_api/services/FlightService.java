package com.pigeon_management_system_api.services;

import com.pigeon_management_system_api.dto.FlightDTO;
import com.pigeon_management_system_api.mappers.FlightMapper;
import com.pigeon_management_system_api.model.Flight;
import com.pigeon_management_system_api.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    public List<FlightDTO> getAllFlights() {
        List<Flight> flightList = flightRepository.findAll();
        return flightMapper.toFlightDTOList(flightList);
    }

    public FlightDTO addFlight(FlightDTO flightDTO) {
        Flight flight = flightMapper.toFlight(flightDTO);
        Flight savedFlight = flightRepository.save(flight);
        return flightMapper.toFlightDTO(savedFlight);
    }

    public void deleteFlight(FlightDTO flightDTO) {
        Flight flight = flightMapper.toFlight(flightDTO);
        flightRepository.delete(flight);
    }

    public FlightDTO updateFlight(FlightDTO flightDTO) {
        Flight flight = flightMapper.toFlight(flightDTO);
        Flight updatedFlight = flightRepository.save(flight);
        return flightMapper.toFlightDTO(updatedFlight);
    }
}
