package com.philippzeppelin.mdalib.repository;

import com.philippzeppelin.mdalib.database.entity.Author;
import com.philippzeppelin.mdalib.database.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Page<Author> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @Query("SELECT b FROM Book b " +
           "LEFT JOIN FETCH b.author a WHERE a.id = :authorId")
    List<Book> findBooksByAuthorId(@Param("authorId") Long authorId);
}
