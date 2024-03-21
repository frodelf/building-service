package com.example.buildingservice.repository;

import com.example.buildingservice.entity.Corps;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorpsRepository extends JpaRepository<Corps, Long> {
}
