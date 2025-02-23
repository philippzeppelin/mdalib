--liquibase formatted sql

--changeset philipp:1
CREATE TABLE IF NOT EXISTS author (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL
);

--changeset philipp:2
CREATE TABLE IF NOT EXISTS book (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    publication_year INT NOT NULL,
    author_id BIGSERIAL NOT NULL,
    FOREIGN KEY (author_id) REFERENCES author(id) ON DELETE CASCADE
);

--changeset philipp:3
CREATE TABLE IF NOT EXISTS availability (
    id BIGSERIAL PRIMARY KEY,
    location VARCHAR(255) NOT NULL,
    quantity INT NOT NULL check (quantity >= 0)
);

--changeset philipp:4
CREATE TABLE IF NOT EXISTS book_availability (
    book_id BIGINT NOT NULL,
    availability_id BIGINT NOT NULL,
    PRIMARY KEY (book_id, availability_id),
    FOREIGN KEY (book_id) REFERENCES book(id) ON DELETE CASCADE,
    FOREIGN KEY (availability_id) REFERENCES availability(id) ON DELETE CASCADE
);