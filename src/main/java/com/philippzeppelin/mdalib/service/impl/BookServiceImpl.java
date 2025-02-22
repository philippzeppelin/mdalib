package com.philippzeppelin.mdalib.service.impl;

import com.philippzeppelin.mdalib.dto.BookDto;
import com.philippzeppelin.mdalib.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    @Override
    public List<BookDto> getBooksByAuthorId(Integer authorId) {
        return List.of();
    }

    @Override
    public void addBook(BookDto bookDto) {

    }

    @Override
    public void deleteBook(Integer bookId) {

    }
}
