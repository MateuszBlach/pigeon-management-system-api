package com.pigeon_management_system_api.controller;

import com.pigeon_management_system_api.model.Pigeon;
import com.pigeon_management_system_api.repository.PigeonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pigeon")
public class PigeonController {

    private static final Logger logger = LoggerFactory.getLogger(PigeonController.class);

    @Autowired
    PigeonRepository pigeonRepository;

    @GetMapping("/{user_id}/all")
    public List<Pigeon> getPigeonsByUserId(@PathVariable("user_id") Integer userId) {

        logger.info("Received request to get all pigeons for user id: "+userId);
        List<Pigeon> pigeons = pigeonRepository.findByUserId(userId);
        logger.info("Found "+pigeons.size()+" pigeons for user id: "+userId);

        return pigeons;
    }
}
