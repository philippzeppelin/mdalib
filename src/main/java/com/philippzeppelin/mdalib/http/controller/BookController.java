package com.philippzeppelin.mdalib.http.controller;

import com.philippzeppelin.mdalib.dto.BookDto;
import com.philippzeppelin.mdalib.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(createdBook);
        } catch (Exception e) { // TODO custom exception handler
            log.error("Error creating new book: {}", e.getMessage()); // TODO custom exception handler
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        log.info("Deleting book: {}", id);
        try {
            bookService.deleteBook(id);
            log.info("Book with ID {} deleted successfully", id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            log.error("Book with ID {} not found", id);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error deleting book: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // TODO исправить
        }
    }
}
