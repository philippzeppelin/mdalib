package com.philippzeppelin.mdalib.mapper;

import com.philippzeppelin.mdalib.database.entity.Availability;
import com.philippzeppelin.mdalib.database.entity.Book;
import com.philippzeppelin.mdalib.dto.AuthorDto;
import com.philippzeppelin.mdalib.dto.AvailabilityDto;
import com.philippzeppelin.mdalib.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookMapper implements Mapper<Book, BookDto> {

    private final AuthorMapper authorMapper;

    @Override
    public BookDto map(Book object) {
        AuthorDto autor = Optional.ofNullable(object.getAuthor())
                .map(authorMapper::map)
                .orElse(null);
        return new BookDto(
                object.getTitle(),
                object.getPublicationYear(),
                autor
        );
    }
}
