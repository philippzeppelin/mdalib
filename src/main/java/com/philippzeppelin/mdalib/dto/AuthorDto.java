package com.philippzeppelin.mdalib.dto;

import lombok.Value;

import java.time.LocalDate;

@Value
public class AuthorDto {
    String name;
    LocalDate birthDate;
}
