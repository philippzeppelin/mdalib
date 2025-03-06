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
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final AvailabilityRepository availabilityRepository;
    private final BookMapper bookMapper;

    /**
     * Adding a book
     * @param bookDto dto containing book details to be saved
     * @return the saved book dto
     */
    @Override
    public BookDto addBook(BookDto bookDto) { // TODO N+1 MDA-1017
        log.info("Searching for author with id: {}", bookDto.getAuthorId());
        Author author = authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found")); // TODO Создать исключение
        List<Availability> availabilities = availabilityRepository.findAllById(bookDto.getAvailabilityIds());
        Book book = Book.builder()
                .title(bookDto.getTitle())
                .publicationYear(bookDto.getPublicationYear())
                .author(author)
                .availabilities(availabilities)
                .build();
        Book savedBook = bookRepository.save(book);
        return bookMapper.mapToDto(savedBook);
    }

    /**
     * Deletes the book
     * @param bookId book ID to delete
     * @throws // TODO забахать исключение кастомное
     */
    @Override
    public void deleteBook(Long bookId) { // TODO N+1 MDA-1017
        log.info("Deleting book with id: {}", bookId);
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> {
                    log.error("Book with ID {} not found", bookId);
                    return new EntityNotFoundException("Book with ID " + bookId + " not found"); // TODO custom exception
                });
        bookRepository.delete(book);
        log.info("Book with ID {} deleted successfully", bookId);
    }
}
