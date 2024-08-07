package com.pigeon_management_system_api.services;

import com.pigeon_management_system_api.dto.PigeonDTO;
import com.pigeon_management_system_api.model.Pigeon;
import com.pigeon_management_system_api.repository.PigeonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PigeonService {

    @Autowired
    private PigeonRepository pigeonRepository;

    public List<Pigeon> getPigeonsByUserId(Integer userId){
        return pigeonRepository.findByUserId(userId);
    }

    public Pigeon addPigeon(PigeonDTO pigeonDTO) {

        Pigeon pigeon = new Pigeon();
        pigeon.setRing(pigeonDTO.getRing());
        pigeon.setGender(pigeonDTO.getGender());
        pigeon.setEyeColor(pigeonDTO.getEyeColor());
        pigeon.setPlumageColor(pigeonDTO.getPlumageColor());
        pigeon.setUserId(pigeonDTO.getUserId());
        return pigeonRepository.save(pigeon);
    }
}
