package com.example.buildingservice.service.impl;

import com.example.buildingservice.entity.Corps;
import com.example.buildingservice.repository.CorpsRepository;
import com.example.buildingservice.service.CorpsService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CorpsServiceImpl implements CorpsService {
    private final CorpsRepository corpsRepository;

    @Override
    public Corps getById(Long corpID) {
        return corpsRepository.findById(corpID).orElseThrow(
                ()-> {
                    throw new EntityNotFoundException("Corps with id="+corpID+" not found");
                }
        );
    }
}