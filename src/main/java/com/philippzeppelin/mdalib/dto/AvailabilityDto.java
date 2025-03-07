package com.philippzeppelin.mdalib.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class AvailabilityDto {
    String location;
    Integer quantity;
}
