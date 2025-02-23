package com.philippzeppelin.mdalib.service;

import com.philippzeppelin.mdalib.dto.BookDto;

public interface BookService {

    BookDto addBook(BookDto bookDto);

    void deleteBook(Long bookId);
}
