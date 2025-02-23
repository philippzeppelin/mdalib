package com.philippzeppelin.mdalib.service;

import com.philippzeppelin.mdalib.dto.AuthorDto;
import com.philippzeppelin.mdalib.dto.BookDto;

import java.util.List;

public interface AuthorService {

    List<AuthorDto> getAuthors(String name, int page, int size);

    AuthorDto saveAuthor(AuthorDto author);

    List<BookDto> findBooksByAuthorId(Long id);
}
