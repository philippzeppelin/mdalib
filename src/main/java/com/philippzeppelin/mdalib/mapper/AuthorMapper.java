package com.philippzeppelin.mdalib.mapper;

import com.philippzeppelin.mdalib.database.entity.Author;
import com.philippzeppelin.mdalib.dto.AuthorDto;
import com.philippzeppelin.mdalib.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthorMapper implements Mapper<Author, AuthorDto> {

//    private final BookMapper bookMapper;

    @Override
    public AuthorDto map(Author object) {
//        List<BookDto> books = object.getBooks().stream()
//                .map(bookMapper::map)
//                .toList();

        return new AuthorDto(
//                object.getId(),
                object.getName(),
                object.getBirthDate() //,
//                books
        );
    }

    public Author map(AuthorDto dto) {
        return Author.builder()
                .name(dto.getName())
                .birthDate(dto.getBirthDate())
                .build();
    }
}
