package com.pigeon_management_system_api.mappers;

import com.pigeon_management_system_api.dto.FlightRecordDTO;
import com.pigeon_management_system_api.model.FlightRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FlightRecordMapper {

    @Mapping(source = "id", target = "id")
    FlightRecordDTO toFlightRecordDTO(FlightRecord flightRecord);

    FlightRecord toFlightRecord(FlightRecordDTO flightRecordDTO);

    List<FlightRecordDTO> toFlightRecordDTOList(List<FlightRecord> flightRecordList);
}
