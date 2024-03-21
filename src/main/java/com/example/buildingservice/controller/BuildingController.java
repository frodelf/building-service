package com.example.buildingservice.controller;

import com.example.buildingservice.dto.BuildingDtoForAdd;
import com.example.buildingservice.dto.BuildingDtoForFilter;
import com.example.buildingservice.dto.BuildingDtoForInformationPage;
import com.example.buildingservice.dto.BuildingDtoForViewAll;
import com.example.buildingservice.entity.enums.StatusState;
import com.example.buildingservice.service.BuildingService;
import com.example.buildingservice.validator.BuildingValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/api/v1/building")
@RequiredArgsConstructor
public class BuildingController {
    private final BuildingService buildingService;
    private final BuildingValidator buildingValidator;
    @GetMapping("/admin/get-all")
    public ResponseEntity<Page<BuildingDtoForViewAll>> getAllBuildingsForAdmin(@RequestParam Integer page, @RequestParam Integer pageSize, @RequestParam String houseName){
        return ResponseEntity.ok(buildingService.getAllForAdmin(page, pageSize, houseName));
    }
    @GetMapping("/admin/get-all-corps-by-building-id/{buildingId}")
    public ResponseEntity<Map<Long, String>> getAllCorps(@PathVariable Long buildingId){
        return ResponseEntity.ok(buildingService.getAllCorpsByBuildingId(buildingId));
    }
    @PutMapping("/admin/delete/{builderId}")
    public ResponseEntity<String> deleteById(@PathVariable Long builderId){
        buildingService.changeStatus(builderId, StatusState.DELETED);
        return ResponseEntity.ok("deleted");
    }
    @PutMapping("/admin/rejected/{builderId}")
    public ResponseEntity<String> rejectedById(@PathVariable Long builderId){
        buildingService.changeStatus(builderId, StatusState.REJECTED);
        return ResponseEntity.ok("changed");
    }
    @PutMapping("/admin/approved/{builderId}")
    public ResponseEntity<String> approvedById(@PathVariable Long builderId){
        buildingService.changeStatus(builderId, StatusState.APPROVED);
        return ResponseEntity.ok("changed");
    }
    @GetMapping("/get-building-for-information-page/{buildingId}")
    public ResponseEntity<BuildingDtoForInformationPage> getBuildingForInformationPage(@PathVariable Long buildingId){
        return ResponseEntity.ok(buildingService.getBuildingForInformationPage(buildingId));
    }
    @GetMapping("/get-all-for-customer")
    public ResponseEntity<Page<BuildingDtoForViewAll>> getAllForCustomer(@ModelAttribute BuildingDtoForFilter buildingDtoForFilter){
        return ResponseEntity.ok(buildingService.getAllForCustomer(buildingDtoForFilter));
    }
    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> add(@ModelAttribute @Valid BuildingDtoForAdd buildingDtoForAdd, BindingResult bindingResult) throws IOException {
        buildingValidator.validate(buildingDtoForAdd, bindingResult);
        Map<String, String> errorsMap = new HashMap<>();
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(error -> errorsMap.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsMap);
        }
        buildingService.add(buildingDtoForAdd);
        return ResponseEntity.ok().body(Collections.singletonMap("status", "saved"));
    }
}