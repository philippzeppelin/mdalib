package com.philippzeppelin.mdalib.repository;

import com.philippzeppelin.mdalib.database.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
