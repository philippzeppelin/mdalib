package com.philippzeppelin.mdalib.database.entity.http.controller;

import com.philippzeppelin.mdalib.dto.AuthorDto;
import com.philippzeppelin.mdalib.dto.BookDto;
import com.philippzeppelin.mdalib.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {
        log.info("Creating new book: {}", bookDto.getTitle());
        try {
            BookDto createdBook = bookService.addBook(bookDto);
            return ResponseEntity.ok().body(createdBook);
        } catch (Exception e) { // TODO custom exception handler
            log.error("Error creating new book: {}", e.getMessage()); // TODO custom exception handler
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
// TODO найти все new ResponseEntity<> и переделать их в ResponseEntity.ok/error