package com.example.buildingservice.dto;

import com.example.buildingservice.entity.Corps;
import com.example.buildingservice.entity.News;
import com.example.buildingservice.entity.enums.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Schema(description = "DTO for adding building")
public class BuildingDtoForAdd {
    private Long id;
    @Size(max = 100, message = "{error.field.size.max}")
    @NotNull(message = "{error.field.empty}")
    @Schema(defaultValue = "RC Warsaw")
    private String name;
    @Size(max = 100, message = "{error.field.size.max}")
    @NotNull(message = "{error.field.empty}")
    @Schema(defaultValue = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula.")
    private String description;
    @Size(max = 100, message = "{error.field.size.max}")
    @NotNull(message = "{error.field.empty}")
    @Schema(defaultValue = "вулиця Соборна, Сарни, Рівненська область, 34500")
    private String address;
    @Max(value = 1000000, message = "{error.field.max-value}")
    @Schema(defaultValue = "24500")
    private Integer minPrice;
    @Max(value = 1000000, message = "{error.field.max-value}")
    @Schema(defaultValue = "2200")
    private Integer pricePerSquareMeter;
    @Max(value = 10000, message = "{error.field.max-value}")
    @Schema(defaultValue = "80")
    private Integer minArea;
    @Max(value = 10000, message = "{error.field.max-value}")
    @Schema(defaultValue = "220")
    private Integer maxArea;
    @Size(max = 100, message = "{error.field.size.max}")
    @NotNull(message = "{error.field.empty}")
    @Schema(defaultValue = "2500")
    private String distanceToSea;
    @Size(max = 100, message = "{error.field.size.max}")
    @NotNull(message = "{error.field.empty}")
    @Schema(defaultValue = "4.3")
    private String ceilingHeight;
    @Size(max = 100, message = "{error.field.size.max}")
    @NotNull(message = "{error.field.empty}")
    @Schema(defaultValue = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula.")
    private String drawingUp;
    @Size(max = 100, message = "{error.field.size.max}")
    @NotNull(message = "{error.field.empty}")
    @Schema(defaultValue = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula.")
    private String calculationOptions;
    @Size(max = 100, message = "{error.field.size.max}")
    @NotNull(message = "{error.field.empty}")
    @Schema(defaultValue = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula.")
    private String aim;
    @Size(max = 100, message = "{error.field.size.max}")
    @NotNull(message = "{error.field.empty}")
    @Schema(defaultValue = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula.")
    private String amountSum;
    @NotNull(message = "{error.field.empty}")
    @Schema(defaultValue = "APPROVED")
    private StatusState statusState;
    @NotNull(message = "{error.field.empty}")
    @Schema(defaultValue = "IN_PLANS")
    private StatusBuilding statusBuilding;
    @NotNull(message = "{error.field.empty}")
    @Schema(defaultValue = "COTTAGES")
    private TypeBuilding typeBuilding;
    @NotNull(message = "{error.field.empty}")
    @Schema(defaultValue = "ELITE")
    private Level level;
    @NotNull(message = "{error.field.empty}")
    @Schema(defaultValue = "MODULAR_HOUSING")
    private ConstructionTechnologies constructionTechnologies;
    @NotNull(message = "{error.field.empty}")
    @Schema(defaultValue = "OPEN")
    private Territory territory;
    @NotNull(message = "{error.field.empty}")
    @Schema(defaultValue = "V380")
    private Electricity electricity;
    @NotNull(message = "{error.field.empty}")
    @Schema(defaultValue = "NATURAL")
    private Gas gas;
    @NotNull(message = "{error.field.empty}")
    @Schema(defaultValue = "CENTRAL")
    private Heating heating;
    @NotNull(message = "{error.field.empty}")
    @Schema(defaultValue = "CENTRAL")
    private Sewage sewage;
    @NotNull(message = "{error.field.empty}")
    @Schema(defaultValue = "CENTRAL")
    private WaterSupply waterSupply;
    @NotNull(message = "{error.field.empty}")
    private List<MultipartFile> images;
    @NotNull(message = "{error.field.empty}")
    private List<MultipartFile> documents;
    @NotNull(message = "{error.field.empty}")
    private List<Corps> corps;
    @NotNull(message = "{error.field.empty}")
    private List<News> news;
    @Schema(defaultValue = "1")
    private Long builder;
}