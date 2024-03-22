package com.example.buildingservice.controller.impl;

import com.example.buildingservice.controller.CorpsController;
import com.example.buildingservice.entity.Corps;
import com.example.buildingservice.service.CorpsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/building")
@RequiredArgsConstructor
public class CorpsControllerImpl implements CorpsController {
    private final CorpsService corpsService;
    @GetMapping("/get-address/{corpID}")
    public ResponseEntity<String> getAddress(@PathVariable Long corpID){
        Corps corps = corpsService.getById(corpID);
        return ResponseEntity.ok(corps.getBuilding().getAddress());
    }
}