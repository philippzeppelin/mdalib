package com.philippzeppelin.mdalib.service;

import com.philippzeppelin.mdalib.database.entity.Book;
import com.philippzeppelin.mdalib.dto.BookDto;

import java.util.List;

public interface BookService {

    /**
     * POST /books
     * Добавление новой книги. Требуется указать название (title), год публикации (publicationYear),
     * автора (authorId) и информацию о наличии (availabilityId).
     *
     * @return
     */
    public BookDto addBook(BookDto bookDto);

    /**
     * DELETE /books/{bookId}
     * Удаление книги по её идентификатору.
     */
    public void deleteBook(Integer bookId);
}
