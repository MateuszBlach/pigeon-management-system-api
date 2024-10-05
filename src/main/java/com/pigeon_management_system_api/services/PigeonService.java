package com.pigeon_management_system_api.services;

import com.pigeon_management_system_api.dto.PigeonDTO;
import com.pigeon_management_system_api.mappers.PigeonMapper;
import com.pigeon_management_system_api.model.Pigeon;
import com.pigeon_management_system_api.repository.PigeonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PigeonService {

    private final PigeonRepository pigeonRepository;

    private final PigeonMapper pigeonMapper;

    public List<PigeonDTO> getPigeonsByUserId(Integer userId){
        List<Pigeon> pigeonList = pigeonRepository.findByUserId(userId);
        return pigeonMapper.toPigeonDTOList(pigeonList);
    }

    public PigeonDTO addPigeon(PigeonDTO pigeonDTO) {

        Pigeon pigeon = pigeonMapper.toPigeon(pigeonDTO);
        Pigeon savedPigeon = pigeonRepository.save(pigeon);
        return pigeonMapper.toPigeonDTO(savedPigeon);
    }
}
