package com.pigeon_management_system_api.services;
import com.pigeon_management_system_api.dto.FlightRecordDTO;
import com.pigeon_management_system_api.mappers.FlightRecordMapper;
import com.pigeon_management_system_api.model.FlightRecord;
import com.pigeon_management_system_api.repository.FlightRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FlightRecordService {

    private final FlightRecordRepository flightRecordRepository;
    private final FlightRecordMapper flightRecordMapper;

    public List<FlightRecordDTO> getAllFlightRecordsForFlight(Integer flightId) {
            List<FlightRecord> flightRecordList = flightRecordRepository.findByFlightId(flightId);
            return flightRecordMapper.toFlightRecordDTOList(flightRecordList);
    }

    public FlightRecordDTO addFlightRecord(FlightRecordDTO flightRecordDTO) {
        FlightRecord flightRecord = flightRecordMapper.toFlightRecord(flightRecordDTO);
        FlightRecord savedFlightRecord = flightRecordRepository.save(flightRecord);
        return flightRecordMapper.toFlightRecordDTO(savedFlightRecord);
    }

    public void deleteFlightRecord(Integer flightRecordId) {
        flightRecordRepository.deleteById(flightRecordId);
    }

    public FlightRecordDTO updateFlightRecord(FlightRecordDTO flightRecordDTO) {
        FlightRecord flightRecord = flightRecordMapper.toFlightRecord(flightRecordDTO);
        FlightRecord updatedFlightRecord = flightRecordRepository.save(flightRecord);
        return flightRecordMapper.toFlightRecordDTO(updatedFlightRecord);
    }
}