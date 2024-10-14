package com.pigeon_management_system_api.mappers;

import com.pigeon_management_system_api.dto.FlightDTO;
import com.pigeon_management_system_api.model.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FlightMapper {

    @Mapping(source = "id", target = "id")
    FlightDTO toFlightDTO(Flight flight);

    Flight toFlight(FlightDTO flightDTO);

    List<FlightDTO> toFlightDTOList(List<Flight> flightList);
}
