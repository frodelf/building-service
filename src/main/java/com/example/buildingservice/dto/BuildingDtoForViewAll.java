package com.example.buildingservice.dto;

import lombok.Data;

@Data
public class BuildingDtoForViewAll {
    private Long id;
    private String image;
    private String name;
    private String address;
    private Integer minPrice;
    private Integer minArea;
}