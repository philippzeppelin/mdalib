package com.philippzeppelin.mdalib.mapper;

import com.philippzeppelin.mdalib.database.entity.Availability;
import com.philippzeppelin.mdalib.dto.AvailabilityDto;
import com.philippzeppelin.mdalib.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AvailabilityMapper implements Mapper<Availability, AvailabilityDto> {
    private final BookMapper bookMapper;

    @Override
    public AvailabilityDto map(Availability object) {
        List<BookDto> books = object.getBooks().stream()
                .map(bookMapper::map).toList();

        return new AvailabilityDto(
                object.getId(),
                object.getLocation(),
                object.getQuantity(),
                books
        );
    }
}
