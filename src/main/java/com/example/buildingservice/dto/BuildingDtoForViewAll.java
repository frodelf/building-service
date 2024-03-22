package com.example.buildingservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO for the page where all buildings will be showed")
public class BuildingDtoForViewAll {
    private Long id;
    private String image;
    private String name;
    private String address;
    private Integer minPrice;
    private Integer minArea;
}