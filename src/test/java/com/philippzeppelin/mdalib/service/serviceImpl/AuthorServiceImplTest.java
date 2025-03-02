package com.philippzeppelin.mdalib.service.serviceImpl;

import com.philippzeppelin.mdalib.database.entity.Author;
import com.philippzeppelin.mdalib.database.entity.Book;
import com.philippzeppelin.mdalib.dto.AuthorDto;
import com.philippzeppelin.mdalib.dto.BookDto;
import com.philippzeppelin.mdalib.mapper.AuthorMapper;
import com.philippzeppelin.mdalib.mapper.BookMapper;
import com.philippzeppelin.mdalib.repository.AuthorRepository;
import com.philippzeppelin.mdalib.service.serviceImpl.util.AuthorServiceUtil;
import com.philippzeppelin.mdalib.service.serviceImpl.util.BookServiceUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private AuthorMapper authorMapper;

    @Mock
    private BookMapper bookMapper;

    @Test
    @DisplayName("Получение всех авторов")
    void getAllAuthors() {
        List<Author> authors = AuthorServiceUtil.getListOfAuthors();
        Page<Author> page = new PageImpl<>(authors);

        when(authorRepository.findByNameContainingIgnoreCase(eq(""), any(Pageable.class)))
                .thenReturn(page);

        when(authorMapper.map(authors.get(0)))
                .thenReturn(new AuthorDto("Муса Джалиль", LocalDate.of(1906, 2, 15)));

        when(authorMapper.map(authors.get(1)))
                .thenReturn(new AuthorDto("Габдулла Тукай", LocalDate.of(1906, 2, 15)));

        List<AuthorDto> result = authorService.getAuthors(null, 0, 100);

        assertEquals(2, result.size());
        assertEquals("Муса Джалиль", result.get(0).getName());
        assertEquals("Габдулла Тукай", result.get(1).getName());

        verify(authorRepository, times(1))
                .findByNameContainingIgnoreCase(eq(""), any(Pageable.class));
    }

    @Test
    @DisplayName("Поиск авторов с пустым результатом")
    void getAllAuthors_EmptyResult() {
        when(authorRepository.findByNameContainingIgnoreCase(eq(""), any(Pageable.class)))
                .thenReturn(Page.empty());
        List<AuthorDto> result = authorService.getAuthors(null, 0, 100);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Поиск авторов по имени")
    void getAllAuthors_byName() {
        List<Author> authors = List.of(AuthorServiceUtil.getAuthor());
        Page<Author> page = new PageImpl<>(authors);
        String searchingAuthorName = "Джалиль";

        when(authorRepository.findByNameContainingIgnoreCase(eq(searchingAuthorName), any(Pageable.class)))
                .thenReturn(page);

        when(authorMapper.map(authors.get(0)))
                .thenReturn(new AuthorDto("Муса Джалиль", LocalDate.of(1906, 2, 15)));

        List<AuthorDto> result = authorService.getAuthors(searchingAuthorName, 0, 100);
        System.out.println(result.toString());

        assertEquals(1, result.size());
        assertEquals("Муса Джалиль", result.get(0).getName());

        verify(authorRepository, times(1))
                .findByNameContainingIgnoreCase(eq(searchingAuthorName), any(Pageable.class));
    }

    @Test
    @DisplayName("Поиск авторов с пагинацией")
    void getAllAuthors_withPagination() {
        List<Author> authors = AuthorServiceUtil.getListOfAuthors();
        List<Author> pagedAuthorList = authors.subList(0, 1);
        Page<Author> page = new PageImpl<>(pagedAuthorList,
                PageRequest.of(0, 1), authors.size());

        when(authorRepository.findByNameContainingIgnoreCase(eq(""), any(Pageable.class)))
                .thenReturn(page);

        when(authorMapper.map(authors.get(0)))
                .thenReturn(new AuthorDto("Муса Джалиль", LocalDate.of(1906, 2, 15)));

        List<AuthorDto> result = authorService.getAuthors(null, 0, 1);

        assertEquals(1, result.size());
        assertEquals("Муса Джалиль", result.get(0).getName());

        verify(authorRepository, times(1))
                .findByNameContainingIgnoreCase(eq(""), any(Pageable.class));
    }

    @Test
    @DisplayName("Сохранение автора")
    void saveAuthor() {
        Author author = AuthorServiceUtil.getAuthor();
        AuthorDto authorDto = new AuthorDto(author.getName(), author.getBirthDate());

        when(authorMapper.map(authorDto)).thenReturn(author);
        when(authorMapper.map(author)).thenReturn(new AuthorDto(author.getName(), author.getBirthDate()));
        when(authorRepository.save(author)).thenReturn(author);

        AuthorDto savedAuthor = authorService.saveAuthor(authorDto);

        assertNotNull(savedAuthor);
        assertEquals(authorDto.getName(), savedAuthor.getName());

        verify(authorRepository, times(1)).save(author);
    }

    @Test
    @DisplayName("Сохранение null author")
    void saveAuthor_withNull() {
        assertThrows(IllegalArgumentException.class, () -> authorService.saveAuthor(null)); // TODO Переделать на кастомный класс-эксешпн
    }

    @Test
    @DisplayName("Сохранение автора с ошибкой")
    void saveAuthor_withRepositoryException() {
        Author author = AuthorServiceUtil.getAuthor();
        AuthorDto authorDto = new AuthorDto(author.getName(), author.getBirthDate());

        when(authorMapper.map(authorDto)).thenReturn(author);
        when(authorRepository.save(author))
                .thenThrow(new RuntimeException("Ошибка сохранении автора")); // TODO Переделать на кастомный класс-эксешпн

        assertThrows(IllegalArgumentException.class, () -> authorService.saveAuthor(authorDto));
    }

    @Test
    @DisplayName("Поиск книг по id автора")
    void findBooksByAuthorId() {
        Long authorId = 1L;
        List<Book> books = BookServiceUtil.getListOfBooks();

        when(authorRepository.findBooksByAuthorId(authorId)).thenReturn(books);
        when(bookMapper.map(books.get(0)))
                .thenReturn(new BookDto("Шурале", 1800, 1L, List.of()));
        when(bookMapper.map(books.get(1)))
                .thenReturn(new BookDto("Водяная", 1801, 1L, List.of()));

        List<BookDto> result = authorService.findBooksByAuthorId(authorId);

        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        verify(authorRepository, times(1)).findBooksByAuthorId(authorId);
    }

    @Test
    @DisplayName("Поиск книг с некорректным id автора")
    void findBooksByAuthorId_withNullAuthorId() {
        assertThrows(IllegalArgumentException.class, () -> authorService.findBooksByAuthorId(null)); // TODO Переделать на кастомный класс-эксешпн
    }

    @Test
    @DisplayName("Поиск книг по id автора с пустым результатом")
    void findBooksByAuthorId_withEmptyResult() {
        Long authorId = 1L;
        when(authorRepository.findBooksByAuthorId(authorId)).thenReturn(List.of());

        List<BookDto> result = authorService.findBooksByAuthorId(authorId);

        assertTrue(result.isEmpty());
        verify(authorRepository, times(1)).findBooksByAuthorId(authorId);
    }
}