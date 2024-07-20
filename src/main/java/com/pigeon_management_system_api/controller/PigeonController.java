package com.pigeon_management_system_api.controller;

import com.pigeon_management_system_api.model.Pigeon;
import com.pigeon_management_system_api.repository.PigeonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pigeon")
public class PigeonController {

    @Autowired
    PigeonRepository pigeonRepository;

    @GetMapping("/{user_id}/all")
    public List<Pigeon> getPigeonsByUserId(@PathVariable("user_id") Integer userId) {
        return pigeonRepository.findByUserId(userId);
    }
}
