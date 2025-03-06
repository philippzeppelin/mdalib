package com.philippzeppelin.mdalib.mapper;

import com.philippzeppelin.mdalib.database.entity.Author;
import com.philippzeppelin.mdalib.dto.AuthorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorMapper implements Mapper<Author, AuthorDto> {

    @Override
    public AuthorDto mapToDto(Author object) {
        return new AuthorDto(
                object.getName(),
                object.getBirthDate()
        );
    }

    public Author mapToEntity(AuthorDto dto) {
        return Author.builder()
                .name(dto.getName())
                .birthDate(dto.getBirthDate())
                .build();
    }
}
