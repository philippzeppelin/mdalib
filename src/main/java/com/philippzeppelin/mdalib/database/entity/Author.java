package com.philippzeppelin.mdalib.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private LocalDate birthDate;

    @OneToMany(mappedBy = "author") // TODO Найти как исправить такую залупу
    private List<Book> books;

    @Override
    public String toString() { // TODO Найти как исправить такую залупу
        return "Author{" +
               "birthDate=" + birthDate +
               ", name='" + name + '\'' +
               ", id=" + id +
               '}';
    }
}
