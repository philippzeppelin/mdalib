package com.philippzeppelin.mdalib.dto;

import lombok.Value;

import java.util.List;

@Value
public class AvailabilityDto {
    Long id;
    String location;
    Integer quantity;
    List<BookDto> book;
}
