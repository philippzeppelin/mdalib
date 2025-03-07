package com.philippzeppelin.mdalib.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Value;

import java.time.LocalDate;

@Value
public class AuthorDto {
    String name;
    LocalDate birthDate;
}
