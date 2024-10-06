package com.pigeon_management_system_api.controller;

import com.pigeon_management_system_api.dto.PigeonDTO;
import com.pigeon_management_system_api.model.Pigeon;
import com.pigeon_management_system_api.repository.PigeonRepository;
import com.pigeon_management_system_api.services.PigeonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pigeon")
public class PigeonController {

    private static final Logger logger = LoggerFactory.getLogger(PigeonController.class);

    @Autowired
    private PigeonService pigeonService;

    @GetMapping("/{user_id}/all")
    public ResponseEntity<List<PigeonDTO>> getPigeonsByUserId(@PathVariable("user_id") Integer userId) {

        logger.info("Received request to get all pigeons for user id: "+userId);
        List<PigeonDTO> pigeons = pigeonService.getPigeonsByUserId(userId);
        logger.info("Found "+pigeons.size()+" pigeons for user id: "+userId);

        return ResponseEntity.ok(pigeons);
    }

    @PostMapping("/add")
    public ResponseEntity<PigeonDTO> addPigeon(@Valid @RequestBody PigeonDTO pigeonDTO){

        PigeonDTO pigeon = pigeonService.addPigeon(pigeonDTO);

        return ResponseEntity.ok(pigeon);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<PigeonDTO> deletePigeon(
            @Valid @RequestBody PigeonDTO pigeonDTO
    ){
        logger.info("Received request to delete pigeon of ring number: "+pigeonDTO.getRing());
        pigeonService.deletePigeon(pigeonDTO);
        return  ResponseEntity.ok(pigeonDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<PigeonDTO> updatePigeon(
            @Valid @RequestBody PigeonDTO pigeonDTO
    ){
        logger.info("Received request to update pigeon of ring number: "+pigeonDTO.getRing());
        PigeonDTO pigeon = pigeonService.updatePigeon(pigeonDTO);
        return ResponseEntity.ok(pigeon);
    }
}
