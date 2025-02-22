package com.philippzeppelin.mdalib.database.entity.http.controller;

import com.philippzeppelin.mdalib.dto.AuthorDto;
import com.philippzeppelin.mdalib.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
public class AuthorController {

    private static final Logger log = LoggerFactory.getLogger(AuthorController.class);
    private final AuthorService authorService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AuthorDto>> getAuthors(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2")int size) { // TODO изменить на 10
            List<AuthorDto> authors = authorService.getAuthors(name, page, size);
            if (authors.isEmpty()) {
                log.warn("No authors found");
            }
            log.info("Found {} authors", authors.size()); // TODO переделать на просто авторы найдены
        return new ResponseEntity<>(authors, !authors.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
