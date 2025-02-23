package com.philippzeppelin.mdalib.service;

import com.philippzeppelin.mdalib.database.entity.Availability;
import com.philippzeppelin.mdalib.dto.AvailabilityDto;

import java.util.List;

public interface AvailabilityService {

    /**
     * GET /availability
     * Получение списка местоположений складов с количеством доступных книг.
     */
    public List<AvailabilityDto> getAllLocations();
}
