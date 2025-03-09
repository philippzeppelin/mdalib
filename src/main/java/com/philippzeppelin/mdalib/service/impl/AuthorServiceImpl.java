package com.philippzeppelin.mdalib.service.impl;

import com.philippzeppelin.mdalib.database.entity.Author;
import com.philippzeppelin.mdalib.database.entity.Book;
import com.philippzeppelin.mdalib.dto.AuthorDto;
import com.philippzeppelin.mdalib.dto.BookDto;
import com.philippzeppelin.mdalib.http.handler.exceptions.author.exception.AuthorNotFoundException;
import com.philippzeppelin.mdalib.http.handler.exceptions.author.exception.AuthorPersistenceException;
import com.philippzeppelin.mdalib.http.handler.exceptions.author.exception.InvalidAuthorException;
import com.philippzeppelin.mdalib.http.handler.exceptions.book.exception.BookNotFoundException;
import com.philippzeppelin.mdalib.mapper.AuthorMapper;
import com.philippzeppelin.mdalib.mapper.BookMapper;
import com.philippzeppelin.mdalib.repository.AuthorRepository;
import com.philippzeppelin.mdalib.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final BookMapper bookMapper;

    /**
     * Retrieves all authors, named author or authors with pagination.
     *
     * @param name the name filter
     * @param page the page number
     * @param size the page size
     * @return a list of AuthorDto objects
     */
    @Override
    public List<AuthorDto> getAuthors(String name, int page, int size) {
        log.info("Get authors with name: {}, page: {}, size: {}", name, page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        List<AuthorDto> authors = authorRepository.findByNameContainingIgnoreCase(name == null ? "" : name, pageable)
                .map(authorMapper::mapToDto).stream()
                .toList();
        if (authors.isEmpty()) {
            log.error("No authors found with name: {}, page: {}, size: {}", name, page, size);
            throw new AuthorNotFoundException("Authors not found");
        }
        log.info("Found {} authors", authors.size());
        return authors;
    }

    /**
     * Saves author
     *
     * @param authorDto dto containing author
     * @return the saved dto author
     * @throws InvalidAuthorException     if author dto is null
     * @throws AuthorPersistenceException if it gets error while saving the author
     */
    @Override
    @Transactional
    public AuthorDto saveAuthor(AuthorDto authorDto) {
        if (authorDto == null) {
            log.error("Attempt to save null AuthorDto");
            throw new InvalidAuthorException("Author DTO cannot be null");
        }
        log.info("Save author: {}", authorDto);
        Author author = authorMapper.mapToEntity(authorDto);
        try {
            Author savedAuthor = authorRepository.save(author);
            log.info("Saved author: {}", savedAuthor);
            return authorMapper.mapToDto(savedAuthor);
        } catch (AuthorPersistenceException e) {
            log.error("Failed to save author: {}", authorDto);
            throw new AuthorPersistenceException("Failed to save author: " + authorDto);
        }
    }

    /**
     * Finds books by author ID
     *
     * @param id author id to find books
     * @return list of books
     * @throws InvalidAuthorException if author ID == null
     * @throws BookNotFoundException  if books not found
     */
    @Override
    public List<BookDto> findBooksByAuthorId(Long id) {
        if (id == null) {
            log.error("Attempt to find books by null AuthorId");
            throw new InvalidAuthorException("Author ID cannot be null");
        }
        List<Book> books = authorRepository.findBooksByAuthorId(id);
        if (books.isEmpty()) {
            log.error("No authors found with id: {}", id);
            throw new BookNotFoundException("Books not found with author id: " + id);
        }
        return books.stream()
                .map(bookMapper::mapToDto)
                .toList();
    }
}
