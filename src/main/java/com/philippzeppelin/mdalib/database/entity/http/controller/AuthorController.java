package com.philippzeppelin.mdalib.database.entity.http.controller;

import com.philippzeppelin.mdalib.dto.AuthorDto;
import com.philippzeppelin.mdalib.dto.BookDto;
import com.philippzeppelin.mdalib.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AuthorDto>> getAuthors(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<AuthorDto> authors = authorService.getAuthors(name, page, size);
        if (authors.isEmpty()) {
            log.warn("No authors found");
        }
        log.info("Found {} authors", authors.size()); // TODO переделать на просто авторы найдены
        return authors.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(authors);
    }

    @PostMapping
    public ResponseEntity<AuthorDto> createNewAuthor(@RequestBody AuthorDto authorDto) {
        log.info("Creating new author {}", authorDto.getName());
        try {
            AuthorDto createdAuthor = authorService.saveAuthor(authorDto);
            return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
        } catch (Exception e) { // TODO custom exception handler
            log.error("Error creating new author: {}", e.getMessage()); // TODO Ошибку переделать
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookDto>> getAuthorBooks(@PathVariable Long id) {
        List<BookDto> books = authorService.findBooksByAuthorId(id);
        if (books.isEmpty()) {
            log.warn("No books found for id {}", id);
            return ResponseEntity.notFound().build();
        }
        log.info("Found books for {}", id);
        return ResponseEntity.ok(books);
    }
}
