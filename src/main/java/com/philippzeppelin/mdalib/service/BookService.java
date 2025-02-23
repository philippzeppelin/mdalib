package com.philippzeppelin.mdalib.service;

import com.philippzeppelin.mdalib.dto.BookDto;

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
     *
     * @return
     */
    public boolean deleteBook(Long bookId);
}
