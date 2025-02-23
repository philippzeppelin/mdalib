package com.philippzeppelin.mdalib.dto;

import lombok.Value;

import java.util.List;

@Value
public class BookDto {
    String title;
    Integer publicationYear;
    Long authorId;
    List<Long> availabilityIds;
}
