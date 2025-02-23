package com.philippzeppelin.mdalib.mapper;

import com.philippzeppelin.mdalib.database.entity.Availability;
import com.philippzeppelin.mdalib.database.entity.Book;
import com.philippzeppelin.mdalib.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookMapper implements Mapper<Book, BookDto> {

    @Override
    public BookDto map(Book object) {
        List<Long> availabilityIds = object.getAvailabilities().stream()
                .map(Availability::getId)
                .toList();
        return new BookDto(
                object.getTitle(),
                object.getPublicationYear(),
                object.getAuthor().getId(),
                availabilityIds
        );
    }
}
