package com.philippzeppelin.mdalib.database.entity.http.controller;

import com.philippzeppelin.mdalib.dto.BookDto;
import com.philippzeppelin.mdalib.repository.BookRepository;
import com.philippzeppelin.mdalib.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookRepository bookRepository;

    @PostMapping
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {
        log.info("Creating new book: {}", bookDto.getTitle());
        try {
            BookDto createdBook = bookService.addBook(bookDto);
            return ResponseEntity.ok().body(createdBook); // TODO Логи
        } catch (Exception e) { // TODO custom exception handler
            log.error("Error creating new book: {}", e.getMessage()); // TODO custom exception handler
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDto> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        log.info("Book with ID {} deleted successfully", id);
        return ResponseEntity.noContent().build();
    }
}
