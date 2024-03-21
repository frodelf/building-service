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
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Order.desc("id")));
        return buildingMapperForViewAll.toDtoPage(buildingRepository.findAllByNameLike(houseName, pageable));
    }
    @Override
    public Page<BuildingDtoForViewAll> getAllForCustomer(BuildingDtoForFilter buildingDtoForFilter) {
        Specification<Building> specification = new BuildingSpecification(buildingDtoForFilter);
        Pageable pageable = PageRequest.of(buildingDtoForFilter.getPage(), buildingDtoForFilter.getPageSize(), Sort.by(Sort.Order.desc("id")));
        Page<BuildingDtoForViewAll> result =  buildingMapperForViewAll.toDtoPage(buildingRepository.findAll(specification, pageable));
        return result;
    }
    @Override
    public Building getById(Long builderId) {
        return buildingRepository.findById(builderId).orElseThrow(
                ()-> {
                    log.error("Building with id={} not found", builderId);
                    return new EntityNotFoundException("Building with id="+builderId+" not found");
                }
        );    }
    @Override
    public Map<Long, String> getAllCorpsByBuildingId(Long builderId) {
        Building building = getById(builderId);
        Map<Long, String> corpses = new HashMap<>();
        for (Corps corps : building.getCorps()) {
            corpses.put(corps.getId(), corps.getName());
        }
        return corpses;
    }
    @Override
    @Transactional
    public void changeStatus(Long builderId, StatusState statusState) {
        Building building = getById(builderId);
        building.setStatusState(statusState);
        save(building);
    }
    @Override
    @Transactional
    public void save(Building building){
        buildingRepository.save(building);
    }
    @Override
    public BuildingDtoForInformationPage getBuildingForInformationPage(Long buildingId){
        return buildingMapperForInformationPage.toDto(getById(buildingId));
    }
    @Override
    @Transactional
    public void add(BuildingDtoForAdd buildingDtoForAdd) throws IOException {
        save(buildingMapperForAdd.updateEntityFromDto(buildingDtoForAdd, this));
    }
}