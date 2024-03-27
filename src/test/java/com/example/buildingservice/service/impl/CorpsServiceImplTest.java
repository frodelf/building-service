package com.example.buildingservice.service.impl;

import com.example.buildingservice.entity.Corps;
import com.example.buildingservice.repository.CorpsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CorpsServiceImplTest {
    @Mock
    private CorpsRepository corpsRepository;
    @InjectMocks
    private CorpsServiceImpl corpsService;
    @Test
    void getById() {
        Long corpId = 1L;
        Corps corps = new Corps();
        when(corpsRepository.findById(corpId)).thenReturn(Optional.of(corps));

        Corps result = corpsService.getById(corpId);

        assertNotNull(result);
        assertSame(corps, result);
        verify(corpsRepository).findById(corpId);
    }
    @Test
    void getByIdWithException() {
        Long corpId = 1L;
        when(corpsRepository.findById(corpId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> corpsService.getById(corpId));
        verify(corpsRepository).findById(corpId);
    }
}