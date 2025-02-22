package com.philippzeppelin.mdalib.service;

import com.philippzeppelin.mdalib.dto.AuthorDto;
import com.philippzeppelin.mdalib.dto.BookDto;

import java.util.List;

public interface AuthorService {

    /**
    GET /authors
    Получение списка всех авторов с возможностью фильтрации по имени (name) и пагинацией (page, size).
     */
//    public List<AuthorDto> getAuthors(); // TODO Снести мб

    public List<AuthorDto> getAuthors(String name, int page, int size);

    /**
     * POST /authors
     * Создание нового автора. Требуется передать имя (name) и дату рождения (birthDate).
     */
    public AuthorDto saveAuthor(AuthorDto author);

    List<BookDto> findBooksByAuthorId(Long id);
}
