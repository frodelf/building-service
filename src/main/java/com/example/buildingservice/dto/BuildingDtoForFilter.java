package com.example.buildingservice.dto;

import com.example.buildingservice.entity.enums.*;
import lombok.Data;

@Data
public class BuildingDtoForFilter {
    private Integer page;
    private Integer pageSize;
    private String name;
    private Integer minPrice;
    private Integer maxPrice;
    private Integer minArea;
    private Integer maxArea;
    private StatusBuilding statusBuilding;
    private TypeBuilding typeBuilding;
    private Electricity electricity;
    private Heating heating;
    private Sewage sewage;
    private WaterSupply waterSupply;
}