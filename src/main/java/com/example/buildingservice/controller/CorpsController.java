package com.example.buildingservice.controller;

import com.example.buildingservice.entity.Corps;
import com.example.buildingservice.service.CorpsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/v1/corps")
@RequiredArgsConstructor
public class CorpsController {
    private final CorpsService corpsService;
    @GetMapping("/get-address/{corpID}")
    public ResponseEntity<String> getAddress(@PathVariable Long corpID){
        Corps corps = corpsService.getById(corpID);
        return ResponseEntity.ok(corps.getBuilding().getAddress());
    }
}