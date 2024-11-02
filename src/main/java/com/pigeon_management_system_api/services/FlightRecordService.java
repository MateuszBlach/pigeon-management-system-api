package com.pigeon_management_system_api.services;
import com.pigeon_management_system_api.dto.FlightRecordDTO;
import com.pigeon_management_system_api.dto.PigeonResultDTO;
import com.pigeon_management_system_api.mappers.FlightRecordMapper;
import com.pigeon_management_system_api.model.Flight;
import com.pigeon_management_system_api.model.FlightRecord;
import com.pigeon_management_system_api.repository.FlightRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<PigeonResultDTO> getAllFlightRecordsForRing(String ring) {
        List<Object[]> flightRecordList = flightRecordRepository.findByPigeonRingWithFlightInfo(ring);

        return flightRecordList.stream().map(record -> {
            FlightRecord flightRecord = (FlightRecord) record[0];
            Flight flight = (Flight) record[1];
            return new PigeonResultDTO(
                    flight.getDistance(),
                    flightRecord.getCoefic(),
                    flightRecord.getPoints(),
                    flight.getDate(),
                    flight.getCity()
            );
        }).collect(Collectors.toList());
    }
}