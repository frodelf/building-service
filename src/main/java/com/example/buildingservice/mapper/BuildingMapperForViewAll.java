package com.example.buildingservice.mapper;

import com.example.buildingservice.dto.BuildingDtoForViewAll;
import com.example.buildingservice.entity.Building;
import com.example.buildingservice.service.client.MinioServiceClient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BuildingMapperForViewAll {
    @Mapping(target = "image", ignore = true)
    BuildingDtoForViewAll toDto(Building building);
    default BuildingDtoForViewAll toDtoFromEntity(Building building) {
        BuildingDtoForViewAll buildingDtoForViewAll = toDto(building);
        if(building.getImages()!=null && building.getImages().get(0)!=null)buildingDtoForViewAll.setImage(MinioServiceClient.getUrl(building.getImages().get(0)));
        return buildingDtoForViewAll;
    }
    default Page<BuildingDtoForViewAll> toDtoPage(Page<Building> buildings){
        return new PageImpl<>(buildings.getContent().stream()
                .map(building -> toDtoFromEntity(building))
                .collect(Collectors.toList()), buildings.getPageable(), buildings.getTotalElements());
    }
}