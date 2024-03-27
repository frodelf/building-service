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
import com.example.buildingservice.service.client.MinioServiceClient;
import com.example.buildingservice.specification.BuildingSpecification;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class BuildingServiceImplTest {
    @Mock
    private BuildingRepository buildingRepository;
    @Mock
    private BuildingMapperForInformationPage buildingMapperForInformationPage;
    @Mock
    private BuildingMapperForViewAll buildingMapperForViewAll;
    @Mock
    private BuildingMapperForAdd buildingMapperForAdd;
    @Mock
    private MinioServiceClient minioServiceClient;
    @InjectMocks
    private BuildingServiceImpl buildingService;
    @Test
    void getAllForAdmin() {
        Page<BuildingDtoForViewAll> buildingPage = mock(Page.class);
        when(buildingRepository.findAllByNameLike(anyString(), any())).thenReturn(mock(Page.class));
        when(buildingMapperForViewAll.toDtoPage(any(), any())).thenReturn(buildingPage);
        Page<BuildingDtoForViewAll> result = buildingService.getAllForAdmin(0, 10, "HouseName");

        assertNotNull(result);
    }

    @Test
    void getAllForCustomer() {
        BuildingDtoForFilter buildingDtoForFilter = new BuildingDtoForFilter();
        buildingDtoForFilter.setPage(0);
        buildingDtoForFilter.setPageSize(10);
        Specification<Building> specification = new BuildingSpecification(buildingDtoForFilter);
        Pageable pageable = PageRequest.of(buildingDtoForFilter.getPage(), buildingDtoForFilter.getPageSize(), Sort.by(Sort.Order.desc("id")));
        Page<BuildingDtoForViewAll> buildingPage = mock(Page.class);
        when(buildingRepository.findAll(specification, pageable)).thenReturn(null);
        when(buildingMapperForViewAll.toDtoPage(any(), any())).thenReturn(buildingPage);

        Page<BuildingDtoForViewAll> result = buildingService.getAllForCustomer(buildingDtoForFilter);

        assertNotNull(result);
    }

    @Test
    void getById() {
        Long builderId = 1L;
        Building building = new Building();
        when(buildingRepository.findById(builderId)).thenReturn(Optional.of(building));

        Building result = buildingService.getById(builderId);

        assertNotNull(result);
        verify(buildingRepository).findById(builderId);
    }

    @Test
    void getAllCorpsByBuildingId() {
        Long builderId = 1L;
        Building building = new Building();
        building.setId(builderId);
        Corps corps = new Corps();
        corps.setId(1L);
        corps.setName("Corps1");
        building.setCorps(Collections.singletonList(corps));
        when(buildingRepository.findById(builderId)).thenReturn(Optional.of(building));

        Map<Long, String> result = buildingService.getAllCorpsByBuildingId(builderId);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Corps1", result.get(1L));
        verify(buildingRepository).findById(builderId);
    }

    @Test
    void changeStatus() {
        Long builderId = 1L;
        Building building = new Building();
        when(buildingRepository.findById(builderId)).thenReturn(Optional.of(building));

        buildingService.changeStatus(builderId, StatusState.DELETED);

        assertEquals(StatusState.DELETED, building.getStatusState());
        verify(buildingRepository).save(building);
    }

    @Test
    void save() {
        Building building = new Building();

        buildingService.save(building);

        verify(buildingRepository).save(building);
    }

    @Test
    void getBuildingForInformationPage() {
        Long buildingId = 1L;
        Building building = new Building();
        BuildingDtoForInformationPage buildingDtoForInformationPage = mock(BuildingDtoForInformationPage.class);
        when(buildingRepository.findById(buildingId)).thenReturn(Optional.of(building));
        when(buildingMapperForInformationPage.toDtoFromEntity(any(), any())).thenReturn(buildingDtoForInformationPage);

        BuildingDtoForInformationPage result = buildingService.getBuildingForInformationPage(buildingId);

        assertNotNull(result);
        assertEquals(result, buildingDtoForInformationPage);
    }

    @Test
    void add() throws IOException {
        BuildingDtoForAdd buildingDtoForAdd = new BuildingDtoForAdd();
        Building building = new Building();
        when(buildingMapperForAdd.updateEntityFromDto(buildingDtoForAdd, minioServiceClient, buildingService)).thenReturn(building);

        buildingService.add(buildingDtoForAdd);

        verify(buildingRepository).save(building);
    }
}