package com.example.buildingservice.service.impl;

import com.example.buildingservice.entity.Corps;
import com.example.buildingservice.repository.CorpsRepository;
import com.example.buildingservice.service.CorpsService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class CorpsServiceImpl implements CorpsService {
    private final CorpsRepository corpsRepository;
    @Override
    public Corps getById(Long corpID) {
        log.info("CorpsServiceImpl-getById start");
        Corps corps = corpsRepository.findById(corpID).orElseThrow(
                ()-> {
                    throw new EntityNotFoundException("Corps with id="+corpID+" not found");
                }
        );
        log.info("CorpsServiceImpl-getById finish");
        return corps;
    }
}