package com.philippzeppelin.mdalib.service.impl;

import com.philippzeppelin.mdalib.dto.AuthorDto;
import com.philippzeppelin.mdalib.mapper.AuthorMapper;
import com.philippzeppelin.mdalib.repository.AuthorRepository;
import com.philippzeppelin.mdalib.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public List<AuthorDto> getAuthors(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        return authorRepository.findByNameContainingIgnoreCase(name == null ? "" : name, pageable)
                .map(authorMapper::map).stream().toList();
    }

    @Override
    public AuthorDto getAuthorByName(String authorName) {
        return null; // TODO
    }
}
