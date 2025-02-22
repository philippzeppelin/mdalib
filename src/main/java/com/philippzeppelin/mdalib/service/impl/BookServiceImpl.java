package com.philippzeppelin.mdalib.service.impl;

import com.philippzeppelin.mdalib.database.entity.Author;
import com.philippzeppelin.mdalib.database.entity.Availability;
import com.philippzeppelin.mdalib.database.entity.Book;
import com.philippzeppelin.mdalib.dto.BookDto;
import com.philippzeppelin.mdalib.mapper.BookMapper;
import com.philippzeppelin.mdalib.repository.AuthorRepository;
import com.philippzeppelin.mdalib.repository.AvailabilityRepository;
import com.philippzeppelin.mdalib.repository.BookRepository;
import com.philippzeppelin.mdalib.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final AvailabilityRepository availabilityRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto addBook(BookDto bookDto) {
        log.warn("Searching for author with id: {}", bookDto.getAuthorId());
        Author author = authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));
        List<Availability> availabilities = availabilityRepository.findAllById(bookDto.getAvailabilityIds());

        Book book = Book.builder()
                .title(bookDto.getTitle())
                .publicationYear(bookDto.getPublicationYear())
                .author(author)
                .availabilities(availabilities)
                .build();
        Book savedBook = bookRepository.save(book);
        return bookMapper.map(savedBook);
    }

    @Override
    public void deleteBook(Integer bookId) {

    }
}
