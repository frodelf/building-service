package com.example.buildingservice.service;

import com.example.buildingservice.dto.BuildingDtoForAdd;
import com.example.buildingservice.dto.BuildingDtoForFilter;
import com.example.buildingservice.dto.BuildingDtoForInformationPage;
import com.example.buildingservice.dto.BuildingDtoForViewAll;
import com.example.buildingservice.entity.Building;
import com.example.buildingservice.entity.enums.StatusState;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.Map;

public interface BuildingService {
    Page<BuildingDtoForViewAll> getAllForAdmin(Integer page, Integer pageSize, String houseName);
    Page<BuildingDtoForViewAll> getAllForCustomer(Integer page, Integer pageSize, BuildingDtoForFilter buildingDtoForFilter);
    Building getById(Long builderId);
    Map<Long, String> getAllCorpsByBuildingId(Long builderId);
    void changeStatus(Long builderId, StatusState statusState);
    void save(Building building);
    BuildingDtoForInformationPage getBuildingForInformationPage(Long buildingId);
    void add(BuildingDtoForAdd buildingDtoForAdd) throws IOException;
}