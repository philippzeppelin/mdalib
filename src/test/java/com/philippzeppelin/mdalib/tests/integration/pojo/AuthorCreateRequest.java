package com.philippzeppelin.mdalib.tests.integration.pojo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AuthorCreateRequest {
    String name;
    LocalDate birthDate;
}
