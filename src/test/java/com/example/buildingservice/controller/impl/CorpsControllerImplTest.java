package com.example.buildingservice.controller.impl;

import com.example.buildingservice.entity.Building;
import com.example.buildingservice.entity.Corps;
import com.example.buildingservice.service.CorpsService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CorpsControllerImplTest {
    @Mock
    private CorpsService corpsService;
    @InjectMocks
    private CorpsControllerImpl corpsController;
    @Test
    void getAddress() {
        Long corpId = 1L;
        String address = "123 Main St";
        Corps corps = new Corps();
        Building building = new Building();
        building.setAddress(address);
        corps.setBuilding(building);
        when(corpsService.getById(corpId)).thenReturn(corps);

        ResponseEntity<String> response = corpsController.getAddress(corpId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(address, response.getBody());
    }
}