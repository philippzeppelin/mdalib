package com.philippzeppelin.mdalib.service.serviceImpl.util;

import com.philippzeppelin.mdalib.database.entity.Author;
import com.philippzeppelin.mdalib.database.entity.Availability;
import com.philippzeppelin.mdalib.database.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookServiceUtil {
//    private static BookDto createBook(String title, Integer publicationYear, Long authorId, List<Long> availabilityIds) {
//        return new BookDto(title, publicationYear, authorId, availabilityIds);
//    }

    private static Book createBook(Long id, String title, Integer publicationYear, Author author, List<Availability> availabilities) {
        return new Book(id, title, publicationYear, author, availabilities);
    }

//    public static List<BookDto> getListOfBooks() {
//        List<BookDto> books = new ArrayList<>();
//        books.add(createBook("Шурале", 1800, 1L, List.of(1L, 2L, 3L)));
//        books.add(createBook("Водяная", 1801, 1L, List.of(1L, 2L, 3L)));
//        return books;
//    }

    public static List<Book> getListOfBooks() {
        List<Book> books = new ArrayList<>();
        books.add(createBook(1L, "Шурале", 1800, AuthorServiceUtil.getAuthor(), List.of()));
        books.add(createBook(1L, "Водяная", 1801, AuthorServiceUtil.getAuthor(), List.of()));
        return books;
    }
}