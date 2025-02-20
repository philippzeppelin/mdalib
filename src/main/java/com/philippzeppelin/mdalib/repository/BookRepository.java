package com.philippzeppelin.mdalib.repository;

import com.philippzeppelin.mdalib.database.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
