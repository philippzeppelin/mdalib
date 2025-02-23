package com.philippzeppelin.mdalib.service.impl;

import com.philippzeppelin.mdalib.database.entity.Availability;
import com.philippzeppelin.mdalib.dto.AvailabilityDto;
import com.philippzeppelin.mdalib.mapper.AvailabilityMapper;
import com.philippzeppelin.mdalib.repository.AvailabilityRepository;
import com.philippzeppelin.mdalib.service.AvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvailabilityServiceImpl implements AvailabilityService {

    private final AvailabilityRepository availabilityRepository;
    private final AvailabilityMapper availabilityMapper;

    @Override
    public List<AvailabilityDto> getAllLocations() { // TODO Логи
        return availabilityRepository.findAll().stream()
                .map(availabilityMapper::map).toList();
    }
}
