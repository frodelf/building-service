package com.example.buildingservice.service.impl;

import com.example.buildingservice.dto.BuildingDtoForAdd;
import com.example.buildingservice.dto.BuildingDtoForFilter;
import com.example.buildingservice.dto.BuildingDtoForInformationPage;
import com.example.buildingservice.dto.BuildingDtoForViewAll;
import com.example.buildingservice.entity.Building;
import com.example.buildingservice.entity.Corps;
import com.example.buildingservice.entity.enums.StatusState;
import com.example.buildingservice.mapper.BuildingMapperForAdd;
import com.example.buildingservice.mapper.BuildingMapperForInformationPage;
import com.example.buildingservice.mapper.BuildingMapperForViewAll;
import com.example.buildingservice.repository.BuildingRepository;
import com.example.buildingservice.service.BuildingService;
import com.example.buildingservice.specification.BuildingSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service
@RequiredArgsConstructor
public class BuildingServiceImpl implements BuildingService {
    private final BuildingRepository buildingRepository;
    private final BuildingMapperForAdd buildingMapperForAdd;
    private final BuildingMapperForViewAll buildingMapperForViewAll;
    private final BuildingMapperForInformationPage buildingMapperForInformationPage;
    @Override
    public Page<BuildingDtoForViewAll> getAllForAdmin(Integer page, Integer pageSize, String houseName) {
        log.info("BuildingServiceImpl-getAllForAdmin start");
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Order.desc("id")));
        Page<BuildingDtoForViewAll> result = buildingMapperForViewAll.toDtoPage(buildingRepository.findAllByNameLike(houseName, pageable));
        log.info("BuildingServiceImpl-getAllForAdmin finish");
        return result;
    }
    @Override
    public Page<BuildingDtoForViewAll> getAllForCustomer(BuildingDtoForFilter buildingDtoForFilter) {
        log.info("BuildingServiceImpl-getAllForCustomer start");
        Specification<Building> specification = new BuildingSpecification(buildingDtoForFilter);
        Pageable pageable = PageRequest.of(buildingDtoForFilter.getPage(), buildingDtoForFilter.getPageSize(), Sort.by(Sort.Order.desc("id")));
        Page<BuildingDtoForViewAll> result =  buildingMapperForViewAll.toDtoPage(buildingRepository.findAll(specification, pageable));
        log.info("BuildingServiceImpl-getAllForCustomer finish");
        return result;
    }
    @Override
    public Building getById(Long builderId) {
        log.info("BuildingServiceImpl-getById start");
        Building building =  buildingRepository.findById(builderId).orElseThrow(
                ()-> {
                    log.error("Building with id={} not found", builderId);
                    return new EntityNotFoundException("Building with id="+builderId+" not found");
                }
        );
        log.info("BuildingServiceImpl-getById finish");
        return building;
    }
    @Override
    public Map<Long, String> getAllCorpsByBuildingId(Long builderId) {
        log.info("BuildingServiceImpl-getAllCorpsByBuildingId start");
        Building building = getById(builderId);
        Map<Long, String> corpses = new HashMap<>();
        for (Corps corps : building.getCorps()) {
            corpses.put(corps.getId(), corps.getName());
        }
        log.info("BuildingServiceImpl-getAllCorpsByBuildingId finish");
        return corpses;
    }
    @Override
    @Transactional
    public void changeStatus(Long builderId, StatusState statusState) {
        log.info("BuildingServiceImpl-changeStatus start");
        Building building = getById(builderId);
        building.setStatusState(statusState);
        save(building);
        log.info("BuildingServiceImpl-changeStatus finish");
    }
    @Override
    @Transactional
    public void save(Building building){
        log.info("BuildingServiceImpl-save start");
        buildingRepository.save(building);
        log.info("BuildingServiceImpl-save finish");
    }
    @Override
    public BuildingDtoForInformationPage getBuildingForInformationPage(Long buildingId){
        log.info("BuildingServiceImpl-getBuildingForInformationPage start");
        BuildingDtoForInformationPage buildingDtoForInformationPage = buildingMapperForInformationPage.toDto(getById(buildingId));
        log.info("BuildingServiceImpl-getBuildingForInformationPage finish");
        return buildingDtoForInformationPage;
    }
    @Override
    @Transactional
    public void add(BuildingDtoForAdd buildingDtoForAdd) throws IOException {
        log.info("BuildingServiceImpl-add start");
        save(buildingMapperForAdd.updateEntityFromDto(buildingDtoForAdd, this));
        log.info("BuildingServiceImpl-add finish");
    }
}