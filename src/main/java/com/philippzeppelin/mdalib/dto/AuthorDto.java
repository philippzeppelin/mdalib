package com.philippzeppelin.mdalib.dto;

import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
public class AuthorDto {
//    Long id;
    String name;
    LocalDate birthDate;
//    List<BookDto> books;
}
