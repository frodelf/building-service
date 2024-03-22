package com.example.buildingservice.dto;

import com.example.buildingservice.entity.enums.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Schema(description = "DTO for the page where building's details will be showed")
public class BuildingDtoForInformationPage {
    private Long id;
    private String name;
    private String description;
    private List<String> images;
    private String address;
    private Integer minPrice;
    private Integer pricePerSquareMeter;
    private Integer minArea;
    private Integer maxArea;
    private Map<Long, String> corps;
    private Map<String, String> news;
    private List<String> documents;

    private StatusBuilding statusBuilding;
    private TypeBuilding typeBuilding;
    private Level level;
    private ConstructionTechnologies constructionTechnologies;
    private Territory territory;
    private String distanceToSea;
    private String ceilingHeight;

    private Electricity electricity;
    private Gas gas;
    private Heating heating;
    private Sewage sewage;
    private WaterSupply waterSupply;
}