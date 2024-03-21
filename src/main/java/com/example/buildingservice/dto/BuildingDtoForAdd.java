package com.example.buildingservice.dto;

import com.example.buildingservice.entity.Corps;
import com.example.buildingservice.entity.News;
import com.example.buildingservice.entity.enums.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class BuildingDtoForAdd {
    private Long id;
    @Size(max = 100, message = "{error.field.size.max}")
    @NotNull(message = "{error.field.empty}")
    private String name;
    @Size(max = 100, message = "{error.field.size.max}")
    @NotNull(message = "{error.field.empty}")
    private String description;
    @Size(max = 100, message = "{error.field.size.max}")
    @NotNull(message = "{error.field.empty}")
    private String address;
    @Max(value = 1000000, message = "{error.field.max-value}")
    private Integer minPrice;
    @Max(value = 1000000, message = "{error.field.max-value}")
    private Integer pricePerSquareMeter;
    @Max(value = 10000, message = "{error.field.max-value}")
    private Integer minArea;
    @Max(value = 10000, message = "{error.field.max-value}")
    private Integer maxArea;
    @Size(max = 100, message = "{error.field.size.max}")
    @NotNull(message = "{error.field.empty}")
    private String distanceToSea;
    @Size(max = 100, message = "{error.field.size.max}")
    @NotNull(message = "{error.field.empty}")
    private String ceilingHeight;
    @Size(max = 100, message = "{error.field.size.max}")
    @NotNull(message = "{error.field.empty}")
    private String drawingUp;
    @Size(max = 100, message = "{error.field.size.max}")
    @NotNull(message = "{error.field.empty}")
    private String calculationOptions;
    @Size(max = 100, message = "{error.field.size.max}")
    @NotNull(message = "{error.field.empty}")
    private String aim;
    @Size(max = 100, message = "{error.field.size.max}")
    @NotNull(message = "{error.field.empty}")
    private String amountSum;
    @NotNull(message = "{error.field.empty}")
    private StatusState statusState;
    @NotNull(message = "{error.field.empty}")
    private StatusBuilding statusBuilding;
    @NotNull(message = "{error.field.empty}")
    private TypeBuilding typeBuilding;
    @NotNull(message = "{error.field.empty}")
    private Level level;
    @NotNull(message = "{error.field.empty}")
    private ConstructionTechnologies constructionTechnologies;
    @NotNull(message = "{error.field.empty}")
    private Territory territory;
    @NotNull(message = "{error.field.empty}")
    private Electricity electricity;
    @NotNull(message = "{error.field.empty}")
    private Gas gas;
    @NotNull(message = "{error.field.empty}")
    private Heating heating;
    @NotNull(message = "{error.field.empty}")
    private Sewage sewage;
    @NotNull(message = "{error.field.empty}")
    private WaterSupply waterSupply;
    @NotNull(message = "{error.field.empty}")
    private List<MultipartFile> images;
    @NotNull(message = "{error.field.empty}")
    private List<MultipartFile> documents;
    @NotNull(message = "{error.field.empty}")
    private List<Corps> corps;
    @NotNull(message = "{error.field.empty}")
    private List<News> news;
    private Long builder;
}