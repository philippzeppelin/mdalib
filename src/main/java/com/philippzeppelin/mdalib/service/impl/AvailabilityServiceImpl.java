package com.philippzeppelin.mdalib.service.impl;

import com.philippzeppelin.mdalib.dto.AvailabilityDto;
import com.philippzeppelin.mdalib.mapper.AvailabilityMapper;
import com.philippzeppelin.mdalib.repository.AvailabilityRepository;
import com.philippzeppelin.mdalib.service.AvailabilityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AvailabilityServiceImpl implements AvailabilityService {

    private final AvailabilityRepository availabilityRepository;
    private final AvailabilityMapper availabilityMapper;

    @Override
    public List<AvailabilityDto> getAllLocations() {
        log.info("Get all locations");
        List<AvailabilityDto> locations = availabilityRepository.findAll().stream()
                .map(availabilityMapper::map)
                .toList();
        if (locations.isEmpty()) {
            log.warn("No locations found");
        }
        return locations;
    }
}
