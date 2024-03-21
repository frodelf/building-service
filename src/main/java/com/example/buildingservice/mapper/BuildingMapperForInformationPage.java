package com.example.buildingservice.mapper;

import com.example.buildingservice.dto.BuildingDtoForInformationPage;
import com.example.buildingservice.entity.Building;
import com.example.buildingservice.entity.Corps;
import com.example.buildingservice.entity.News;
import com.example.buildingservice.service.client.MinioServiceClient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.HashMap;

@Mapper(componentModel = "spring")
public interface BuildingMapperForInformationPage {
    @Mappings({
            @Mapping(target = "images", ignore = true),
            @Mapping(target = "corps", ignore = true),
            @Mapping(target = "news", ignore = true),
            @Mapping(target = "documents", ignore = true)
    })
    BuildingDtoForInformationPage toDto(Building building);
    default BuildingDtoForInformationPage toDtoFromEntity(Building building){
        BuildingDtoForInformationPage buildingDtoForInformationPage = toDto(building);
        buildingDtoForInformationPage.setCorps(new HashMap<>());
        buildingDtoForInformationPage.setNews(new HashMap<>());
        if(building.getCorps()!=null) {
            for (Corps corps : building.getCorps()) {
                buildingDtoForInformationPage.getCorps().put(corps.getId(), corps.getName());
            }
        }
        if(building.getNewses()!=null) {
            for (News news : building.getNewses()) {
                buildingDtoForInformationPage.getNews().put(news.getTitle(), news.getDescription());
            }
        }
        buildingDtoForInformationPage.setImages(MinioServiceClient.getUrl(building.getImages()));
        buildingDtoForInformationPage.setDocuments(MinioServiceClient.getUrl(building.getDocuments()));
        return buildingDtoForInformationPage;
    }
}