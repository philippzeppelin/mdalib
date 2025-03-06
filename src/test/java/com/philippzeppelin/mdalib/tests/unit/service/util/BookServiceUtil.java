package com.philippzeppelin.mdalib.tests.unit.service.util;

import com.philippzeppelin.mdalib.database.entity.Author;
import com.philippzeppelin.mdalib.database.entity.Availability;
import com.philippzeppelin.mdalib.database.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookServiceUtil {
    private static Book createBook(Long id, String title, Integer publicationYear, Author author, List<Availability> availabilities) {
        return new Book(id, title, publicationYear, author, availabilities);
    }

    public static List<Book> getListOfBooks() {
        List<Book> books = new ArrayList<>();
        books.add(createBook(1L, "Шурале", 1800, AuthorServiceUtil.getAuthor(), List.of()));
        books.add(createBook(1L, "Водяная", 1801, AuthorServiceUtil.getAuthor(), List.of()));
        return books;
    }
}