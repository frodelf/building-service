package com.example.buildingservice.repository;

import com.example.buildingservice.entity.Building;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BuildingRepository extends JpaRepository<Building, Long> , JpaSpecificationExecutor<Building> {
    Page<Building> findAllByNameLike(String name, Pageable pageable);
}