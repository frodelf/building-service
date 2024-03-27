package com.example.buildingservice.controller.impl;

import com.example.buildingservice.dto.BuildingDtoForAdd;
import com.example.buildingservice.dto.BuildingDtoForFilter;
import com.example.buildingservice.dto.BuildingDtoForInformationPage;
import com.example.buildingservice.dto.BuildingDtoForViewAll;
import com.example.buildingservice.entity.enums.StatusState;
import com.example.buildingservice.service.BuildingService;
import com.example.buildingservice.validator.BuildingValidator;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class BuildingControllerImplTest {
    @Mock
    private BuildingService buildingService;
    @Mock
    private BuildingValidator buildingValidator;
    @InjectMocks
    private BuildingControllerImpl buildingController;
    @Test
    void getAllBuildingsForAdmin() {
        Page<BuildingDtoForViewAll> page = mock(Page.class);
        when(buildingService.getAllForAdmin(anyInt(), anyInt(), anyString())).thenReturn(page);

        ResponseEntity<Page<BuildingDtoForViewAll>> response = buildingController.getAllBuildingsForAdmin(1, 10, "HouseName");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(page, response.getBody());
    }
    @Test
    void getAllCorps() {
        Map<Long, String> corpsMap = new HashMap<>();
        corpsMap.put(1L, "Corps1");
        when(buildingService.getAllCorpsByBuildingId(anyLong())).thenReturn(corpsMap);

        ResponseEntity<Map<Long, String>> response = buildingController.getAllCorps(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(corpsMap, response.getBody());
    }
    @Test
    void deleteById() {
        ResponseEntity<String> response = buildingController.deleteById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("deleted", response.getBody());
        verify(buildingService).changeStatus(1L, StatusState.DELETED);
    }
    @Test
    void rejectedById() {
        ResponseEntity<String> response = buildingController.rejectedById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("changed", response.getBody());
        verify(buildingService).changeStatus(1L, StatusState.REJECTED);
    }
    @Test
    void approvedById() {
        ResponseEntity<String> response = buildingController.approvedById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("changed", response.getBody());
        verify(buildingService).changeStatus(1L, StatusState.APPROVED);
    }
    @Test
    void getBuildingForInformationPage() {
        BuildingDtoForInformationPage buildingDtoForInformationPage = mock(BuildingDtoForInformationPage.class);
        when(buildingService.getBuildingForInformationPage(anyLong())).thenReturn(buildingDtoForInformationPage);

        ResponseEntity<BuildingDtoForInformationPage> response = buildingController.getBuildingForInformationPage(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(buildingDtoForInformationPage, response.getBody());
    }
    @Test
    void getAllForCustomer() {
        Page<BuildingDtoForViewAll> page = mock(Page.class);
        BuildingDtoForFilter buildingDtoForFilter = new BuildingDtoForFilter();
        when(buildingService.getAllForCustomer(buildingDtoForFilter)).thenReturn(page);

        ResponseEntity<Page<BuildingDtoForViewAll>> response = buildingController.getAllForCustomer(buildingDtoForFilter);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(page, response.getBody());
    }
    @Test
    void add() throws IOException {
        BuildingDtoForAdd buildingDtoForAdd = new BuildingDtoForAdd();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        ResponseEntity<Map<String, String>> response = buildingController.add(buildingDtoForAdd, bindingResult);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.singletonMap("status", "saved"), response.getBody());
        verify(buildingService).add(buildingDtoForAdd);
    }
    @Test
    void addWithError() throws IOException {
        BuildingDtoForAdd buildingDtoForAdd = new BuildingDtoForAdd();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getFieldErrors()).thenReturn(Collections.singletonList(new FieldError("field", "field","message")));

        ResponseEntity<Map<String, String>> response = buildingController.add(buildingDtoForAdd, bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(Collections.singletonMap("field", "message"), response.getBody());
        verify(buildingService, never()).add(any());
    }
}