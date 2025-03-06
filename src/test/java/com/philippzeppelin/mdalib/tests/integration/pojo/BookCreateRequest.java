package com.philippzeppelin.mdalib.tests.integration.pojo;

import lombok.Data;

import java.util.List;

@Data
public class BookCreateRequest {
    String title;
    Integer publicationYear;
    Long authorId;
    List<Long> availabilityIds;
}
