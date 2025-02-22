package com.philippzeppelin.mdalib.dto;

import lombok.Value;

import java.util.List;

@Value
public class BookDto {
//    Long id;
    String title;
    Integer publicationYear;
    AuthorDto author;
//    List<AvailabilityDto> availability; // TODO availabilities?
}
