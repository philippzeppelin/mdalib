package com.philippzeppelin.mdalib.mapper;

import com.philippzeppelin.mdalib.database.entity.Availability;
import com.philippzeppelin.mdalib.dto.AvailabilityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AvailabilityMapper implements Mapper<Availability, AvailabilityDto> {

    @Override
    public AvailabilityDto mapToDto(Availability object) {
        return new AvailabilityDto(
                object.getLocation(),
                object.getQuantity()
        );
    }
}
