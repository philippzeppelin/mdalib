package com.philippzeppelin.mdalib.http.controller;

import com.philippzeppelin.mdalib.dto.AvailabilityDto;
import com.philippzeppelin.mdalib.service.AvailabilityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/availabilities")
@RequiredArgsConstructor
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    @GetMapping
    public ResponseEntity<List<AvailabilityDto>> getAvailabilities() {
        log.info("Retrieving availability");
        List<AvailabilityDto> availabilities = availabilityService.getAllLocations();
        if (availabilities.isEmpty()) {
            log.warn("No availability found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info("Found {} availability", availabilities.size());
        return ResponseEntity.status(HttpStatus.OK).body(availabilities);
    }
}
