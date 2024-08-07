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
    public ResponseEntity<List<Pigeon>> getPigeonsByUserId(@PathVariable("user_id") Integer userId) {

        logger.info("Received request to get all pigeons for user id: "+userId);
        List<Pigeon> pigeons = pigeonService.getPigeonsByUserId(userId);
        logger.info("Found "+pigeons.size()+" pigeons for user id: "+userId);

        return ResponseEntity.ok(pigeons);
    }

    @PostMapping("/add")
    public ResponseEntity<Pigeon> addPigeon(@Valid @RequestBody PigeonDTO pigeonDTO){

        Pigeon pigeon = pigeonService.addPigeon(pigeonDTO);

        return ResponseEntity.ok(pigeon);
    }
}
