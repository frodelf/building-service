package com.example.buildingservice.mapper;

import com.example.buildingservice.dto.BuildingDtoForAdd;
import com.example.buildingservice.entity.Building;
import com.example.buildingservice.service.BuildingService;
import com.example.buildingservice.service.client.MinioServiceClient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.io.IOException;

@Mapper(componentModel = "spring")
public interface BuildingMapperForAdd {
    @Mappings({
            @Mapping(target = "images", ignore = true),
            @Mapping(target = "corps", ignore = true),
            @Mapping(target = "newses", ignore = true),
            @Mapping(target = "documents", ignore = true)
    })
    void updateEntityFromDto(BuildingDtoForAdd buildingDtoForAdd, @MappingTarget Building building);
    default Building updateEntityFromDto(BuildingDtoForAdd buildingDtoForAdd, BuildingService buildingService) throws IOException {
        Building building = new Building();
        if(buildingDtoForAdd.getId()!=null)building = buildingService.getById(buildingDtoForAdd.getId());
        updateEntityFromDto(buildingDtoForAdd, building);
        if(buildingDtoForAdd.getImages()!=null)
            building.getImages().addAll(MinioServiceClient.saveAll(buildingDtoForAdd.getImages()));
        if(buildingDtoForAdd.getDocuments()!=null)
            building.getImages().addAll(MinioServiceClient.saveAll(buildingDtoForAdd.getDocuments()));
        if(buildingDtoForAdd.getCorps()!=null)
            building.getCorps().addAll(buildingDtoForAdd.getCorps());
        if(buildingDtoForAdd.getNews()!=null)
            building.getNewses().addAll(buildingDtoForAdd.getNews());
        return building;
    }

}