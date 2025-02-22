package com.philippzeppelin.mdalib.repository;

import com.philippzeppelin.mdalib.database.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

//    @Query("SELECT a FROM Author a WHERE :name IS NULL OR LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%'))")
//    Page<Author> findByName(@Param("name") String name, Pageable pageable);

    Page<Author> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
