package com.philippzeppelin.mdalib.service;

import java.util.List;

public interface AvailabilityService {

    /**
     * GET /availability
     * Получение списка местоположений складов с количеством доступных книг.
     */
    public List<String> getAllLocations();
}
