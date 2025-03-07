package com.philippzeppelin.mdalib.http.controller;

import com.philippzeppelin.mdalib.dto.AuthorDto;
import com.philippzeppelin.mdalib.dto.BookDto;
import com.philippzeppelin.mdalib.service.AuthorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size) {
        List<AuthorDto> authors = authorService.getAuthors(name, page, size);
        if (authors.isEmpty()) {
            log.warn("No authors found");
            return ResponseEntity.notFound().build();
        }
        log.info("Found {} authors", authors.size());
        return ResponseEntity.ok(authors);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorDto> createNewAuthor(@Valid @NotNull @RequestBody AuthorDto authorDto) {
        log.info("Creating new author {}", authorDto.getName());
        AuthorDto createdAuthor = authorService.saveAuthor(authorDto);
        log.info("Created author: {}", createdAuthor);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAuthor);
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookDto>> getBooksByAuthorId(@PathVariable Long id) { // TODO N+1 MDA-1017
        log.info("Retrieving author books for author {}", id);
        List<BookDto> books = authorService.findBooksByAuthorId(id);
        log.info("Found books for {}", id);
        return ResponseEntity.ok(books);
    }
}
