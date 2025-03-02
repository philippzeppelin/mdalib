package com.philippzeppelin.mdalib.service.serviceImpl.util;

import com.philippzeppelin.mdalib.database.entity.Author;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AuthorServiceUtil {
    // перекинуть сюда создание авторов
    public static Author createAuthor(String name, LocalDate birthDate) {
        Author author = Author.builder() // Создать отдельный файл с static // вынести в отдельный файл
                .name(name)
                .birthDate(birthDate)
                .build();
        return author;
    }

    public static Author getAuthor() {
        return createAuthor("Муса Джалиль", LocalDate.of(1906, 2, 15));
    }

    public static List<Author> getListOfAuthors() {
        List<Author> authors = new ArrayList<>();
        authors.add(createAuthor("Муса Джалиль", LocalDate.of(1906, 2, 15)));
        authors.add(createAuthor("Хади Такташ", LocalDate.of(1922, 2, 16)));
        return authors;
    }
}
