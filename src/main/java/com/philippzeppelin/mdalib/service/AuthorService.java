package com.philippzeppelin.mdalib.service;

import com.philippzeppelin.mdalib.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    /**
    GET /authors
    Получение списка всех авторов с возможностью фильтрации по имени (name) и пагинацией (page, size).
     */
    public List<AuthorDto> getAuthors();

    /**
     * POST /authors
     * Создание нового автора. Требуется передать имя (name) и дату рождения (birthDate).
     */
    public AuthorDto getAuthorByName(String authorName);
}
