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
//    private final AvailabilityMapper availabilityMapper;

    @Override
    public BookDto map(Book object) {
        // TODO Написать маппер
        AuthorDto author = Optional.ofNullable(object.getAuthor())
                .map(authorMapper::map)
                .orElse(null);

//        List<AvailabilityDto> availabilities = object.getAvailabilities().stream()
//                .map(availabilityMapper::map)
//                .toList();

//        Availability availability = Optional.ofNullable(object.getAvailabilities())
//        .map(availabilityMapper::map)
//                .orElse(null);

        return new BookDto(
                object.getId(),
                object.getTitle(),
                object.getPublicationYear(),
                author//,
//                availabilities
        );
    }

//    @Override
//    public BookDto map(Book fromOjbect, BookDto toObject) {
//        return Mapper.super.map(fromOjbect, toObject);
//    }
}
