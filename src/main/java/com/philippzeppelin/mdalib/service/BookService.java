package com.philippzeppelin.mdalib.service;

import com.philippzeppelin.mdalib.dto.BookDto;

import java.util.List;

public interface BookService {

    /**
     * GET /authors/{authorId}/books
     * Получение списка книг, написанных конкретным автором.
     */
    public List<BookDto> getBooksByAuthorId(Integer authorId);

    /**
     * POST /books
     * Добавление новой книги. Требуется указать название (title), год публикации (publicationYear),
     * автора (authorId) и информацию о наличии (availabilityId).
     */
    public void addBook(BookDto bookDto);

    /**
     * DELETE /books/{bookId}
     * Удаление книги по её идентификатору.
     */
    public void deleteBook(Integer bookId);
}
