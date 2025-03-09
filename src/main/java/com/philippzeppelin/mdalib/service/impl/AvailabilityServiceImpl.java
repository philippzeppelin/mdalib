package com.philippzeppelin.mdalib.service.impl;

import com.philippzeppelin.mdalib.dto.AvailabilityDto;
import com.philippzeppelin.mdalib.http.handler.exceptions.availability.exception.AvailabilitiesNotFoundException;
import com.philippzeppelin.mdalib.mapper.AvailabilityMapper;
import com.philippzeppelin.mdalib.repository.AvailabilityRepository;
import com.philippzeppelin.mdalib.service.AvailabilityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AvailabilityServiceImpl implements AvailabilityService {

    private final AvailabilityRepository availabilityRepository;
    private final AvailabilityMapper availabilityMapper;

    /**
     * Gets all availabilities
     *
     * @return list of availabilities
     */
    @Override
    public List<AvailabilityDto> getAllAvailabilities() {
        log.info("Get all availabilities");
        List<AvailabilityDto> availabilities = availabilityRepository.findAll().stream()
                .map(availabilityMapper::mapToDto)
                .toList();
        if (availabilities.isEmpty()) {
            log.error("Availabilities not found");
            throw new AvailabilitiesNotFoundException("Availabilities not found");
        }
        log.info("Found {} availabilities", availabilities.size());
        return availabilities;
    }
}
