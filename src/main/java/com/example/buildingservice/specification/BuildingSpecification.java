package com.example.buildingservice.specification;

import com.example.buildingservice.dto.BuildingDtoForFilter;
import com.example.buildingservice.entity.Building;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BuildingSpecification implements Specification<Building> {
    private BuildingDtoForFilter buildingDtoForFilter;

    public BuildingSpecification(BuildingDtoForFilter buildingDtoForFilter) {
        this.buildingDtoForFilter = buildingDtoForFilter;
    }

    @Override
    public Predicate toPredicate(Root<Building> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if(buildingDtoForFilter.getName()!=null) {
            predicates.add(criteriaBuilder.like(root.get("name"), buildingDtoForFilter.getName()));
        }
        if(buildingDtoForFilter.getMinPrice()!=null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("pricePerSquareMeter"), buildingDtoForFilter.getMinPrice()));
        }
        if(buildingDtoForFilter.getMaxPrice()!=null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("pricePerSquareMeter"), buildingDtoForFilter.getMaxPrice()));
        }
        if(buildingDtoForFilter.getMinArea()!=null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("minArea"), buildingDtoForFilter.getMinArea()));
        }
        if(buildingDtoForFilter.getMaxArea()!=null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("maxArea"), buildingDtoForFilter.getMaxArea()));
        }

        if(buildingDtoForFilter.getStatusBuilding()!=null) {
            predicates.add(criteriaBuilder.equal(root.get("statusBuilding"), buildingDtoForFilter.getStatusBuilding()));
        }
        if(buildingDtoForFilter.getTypeBuilding()!=null) {
            predicates.add(criteriaBuilder.equal(root.get("typeBuilding"), buildingDtoForFilter.getTypeBuilding()));
        }
        if(buildingDtoForFilter.getElectricity()!=null) {
            predicates.add(criteriaBuilder.equal(root.get("electricity"), buildingDtoForFilter.getElectricity()));
        }
        if(buildingDtoForFilter.getHeating()!=null) {
            predicates.add(criteriaBuilder.equal(root.get("heating"), buildingDtoForFilter.getHeating()));
        }
        if(buildingDtoForFilter.getSewage()!=null) {
            predicates.add(criteriaBuilder.equal(root.get("sewage"), buildingDtoForFilter.getSewage()));
        }
        if(buildingDtoForFilter.getWaterSupply()!=null) {
            predicates.add(criteriaBuilder.equal(root.get("waterSupply"), buildingDtoForFilter.getWaterSupply()));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
