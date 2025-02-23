package com.philippzeppelin.mdalib.service.impl;

import com.philippzeppelin.mdalib.database.entity.Author;
import com.philippzeppelin.mdalib.dto.AuthorDto;
import com.philippzeppelin.mdalib.dto.BookDto;
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

    @Override
    public List<AuthorDto> getAuthors(String name, int page, int size) { // TODO getAll N+1 MDA-1017 // по имени нет N+1 // пагинация N+1
        log.info("Get authors with name: {}, page: {}, size: {}", name, page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        List<AuthorDto> authors = authorRepository.findByNameContainingIgnoreCase(name == null ? "" : name, pageable)
                .map(authorMapper::map).stream()
                .toList();
        log.info("Found {} authors", authors.size());
        return authors;
    }

    @Override
    @Transactional
    public AuthorDto saveAuthor(AuthorDto authorDto) {
        if (authorDto == null) {
            log.error("Attempt to save null AuthorDto");
            throw new IllegalArgumentException("Attempt to save null AuthorDto"); // TODO Создать исключение
        }
        log.info("Save author: {}", authorDto);
        Author author = authorMapper.map(authorDto);
        try {
            Author savedAuthor = authorRepository.save(author);
            log.info("Saved author: {}", savedAuthor);
            return authorMapper.map(savedAuthor);
        } catch (Exception e) {
            log.error("Failed to save author: {}", authorDto);
            throw new IllegalArgumentException("Failed to save author: " + authorDto); // TODO Создать исключение
        }
    }

    @Override
    public List<BookDto> findBooksByAuthorId(Long id) {
        log.info("Find books by authorId: {}", id);
        List<BookDto> books = authorRepository.findBooksByAuthorId(id).stream()
                .map(bookMapper::map)
                .toList();
        if (books.isEmpty()) {
            log.warn("No books found for author: {}", id);
        } else {
            log.info("Found {} books", books.size());
        }
        return books;
    }
}
