package com.example.buildingservice.controller;

import com.example.buildingservice.dto.BuildingDtoForAdd;
import com.example.buildingservice.dto.BuildingDtoForFilter;
import com.example.buildingservice.dto.BuildingDtoForInformationPage;
import com.example.buildingservice.dto.BuildingDtoForViewAll;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Tag(name = "Building controller", description = "Building API")
public interface BuildingController {
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authorized"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
    })
    @Operation(summary = "The request to get all buildings for admin panel by house name(like)")
    ResponseEntity<Page<BuildingDtoForViewAll>> getAllBuildingsForAdmin(@Parameter(description = "Page for pagination") @RequestParam Integer page, @Parameter(description = "Page size for page numbering") @RequestParam Integer pageSize, @Parameter(description = "House name for filtering") @RequestParam String houseName);
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authorized"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
    })
    @Operation(summary = "The request to get all corpses by building id")
    ResponseEntity<Map<Long, String>> getAllCorps(@Parameter(description = "Building id by which all corpses will be found") @PathVariable Long buildingId);
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authorized"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
    })
    @Operation(summary = "The request to delete building by id")
    ResponseEntity<String> deleteById(@Parameter(description = "Building id by which building will be deleted") @PathVariable Long builderId);
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authorized"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
    })
    @Operation(summary = "The request to reject building by id")
    ResponseEntity<String> rejectedById(@Parameter(description = "Building id by which building will be rejected") @PathVariable Long builderId);
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authorized"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
    })
    @Operation(summary = "The request to approved building by id")
    ResponseEntity<String> approvedById(@Parameter(description = "Building id by which building will be approved") @PathVariable Long builderId);
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authorized"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
    })
    @Operation(summary = "The request to get building by id for information page")
    ResponseEntity<BuildingDtoForInformationPage> getBuildingForInformationPage(@Parameter(description = "Building id for information part") @PathVariable Long buildingId);
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authorized"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
    })
    @Operation(summary = "The request to get all buildings for customer and filtering this objects")
    ResponseEntity<Page<BuildingDtoForViewAll>> getAllForCustomer(@RequestBody(description = "DTO for filtering buildings") @ModelAttribute BuildingDtoForFilter buildingDtoForFilter);
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authorized"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
    })
    @Operation(summary = "The request to add building")
    ResponseEntity<Map<String, String>> add(@RequestBody(description = "DTO for adding/updating to building") @ModelAttribute @Valid BuildingDtoForAdd buildingDtoForAdd, BindingResult bindingResult) throws IOException ;
}