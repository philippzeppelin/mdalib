-- Вставка авторов
INSERT INTO author (name, birth_date)
VALUES ('Александр Пушкин', '1799-06-06'),
       ('Фёдор Достоевский', '1821-11-11'),
       ('Лев Толстой', '1828-09-09'),
       ('Антон Чехов', '1860-01-29'),
       ('Николай Гоголь', '1809-04-01');

-- Вставка книг для Пушкина
INSERT INTO book (title, publication_year, author_id)
VALUES ('Евгений Онегин', 1833, (SELECT id FROM author WHERE name = 'Александр Пушкин')),
       ('Капитанская дочка', 1836, (SELECT id FROM author WHERE name = 'Александр Пушкин')),
       ('Руслан и Людмила', 1820, (SELECT id FROM author WHERE name = 'Александр Пушкин')),
       ('Пиковая дама', 1834, (SELECT id FROM author WHERE name = 'Александр Пушкин')),
       ('Медный всадник', 1837, (SELECT id FROM author WHERE name = 'Александр Пушкин'));

-- Книги Достоевского
INSERT INTO book (title, publication_year, author_id)
VALUES ('Преступление и наказание', 1866, (SELECT id FROM author WHERE name = 'Фёдор Достоевский')),
       ('Идиот', 1869, (SELECT id FROM author WHERE name = 'Фёдор Достоевский')),
       ('Братья Карамазовы', 1880, (SELECT id FROM author WHERE name = 'Фёдор Достоевский')),
       ('Бесы', 1872, (SELECT id FROM author WHERE name = 'Фёдор Достоевский')),
       ('Записки из подполья', 1864, (SELECT id FROM author WHERE name = 'Фёдор Достоевский'));

-- Книги Толстого
INSERT INTO book (title, publication_year, author_id)
VALUES ('Война и мир', 1869, (SELECT id FROM author WHERE name = 'Лев Толстой')),
       ('Анна Каренина', 1877, (SELECT id FROM author WHERE name = 'Лев Толстой')),
       ('Воскресение', 1899, (SELECT id FROM author WHERE name = 'Лев Толстой')),
       ('Смерть Ивана Ильича', 1886, (SELECT id FROM author WHERE name = 'Лев Толстой')),
       ('Хаджи-Мурат', 1912, (SELECT id FROM author WHERE name = 'Лев Толстой'));

-- Книги Чехова
INSERT INTO book (title, publication_year, author_id)
VALUES ('Вишнёвый сад', 1904, (SELECT id FROM author WHERE name = 'Антон Чехов')),
       ('Чайка', 1896, (SELECT id FROM author WHERE name = 'Антон Чехов')),
       ('Три сестры', 1901, (SELECT id FROM author WHERE name = 'Антон Чехов')),
       ('Палата №6', 1892, (SELECT id FROM author WHERE name = 'Антон Чехов')),
       ('Дама с собачкой', 1899, (SELECT id FROM author WHERE name = 'Антон Чехов'));

-- Книги Гоголя
INSERT INTO book (title, publication_year, author_id)
VALUES ('Мёртвые души', 1842, (SELECT id FROM author WHERE name = 'Николай Гоголь')),
       ('Ревизор', 1836, (SELECT id FROM author WHERE name = 'Николай Гоголь')),
       ('Тарас Бульба', 1835, (SELECT id FROM author WHERE name = 'Николай Гоголь')),
       ('Шинель', 1842, (SELECT id FROM author WHERE name = 'Николай Гоголь')),
       ('Вечера на хуторе близ Диканьки', 1831, (SELECT id FROM author WHERE name = 'Николай Гоголь'));

-- Локации хранения
INSERT INTO availability (id, location, quantity)
VALUES (1, 'Главный склад', 100),
       (2, 'Филиал №1 (Москва)', 50),
       (3, 'Филиал №2 (СПб)', 30),
       (4, 'Филиал №3 (Казань)', 40);

-- Связь книг с локациями
INSERT INTO book_availability (book_id, availability_id)
VALUES ((SELECT id FROM book WHERE title = 'Евгений Онегин'), 1),
       ((SELECT id FROM book WHERE title = 'Евгений Онегин'), 2),
       ((SELECT id FROM book WHERE title = 'Евгений Онегин'), 4),
       ((SELECT id FROM book WHERE title = 'Преступление и наказание'), 1),
       ((SELECT id FROM book WHERE title = 'Преступление и наказание'), 3),
       ((SELECT id FROM book WHERE title = 'Преступление и наказание'), 4),
       ((SELECT id FROM book WHERE title = 'Война и мир'), 1),
       ((SELECT id FROM book WHERE title = 'Война и мир'), 4),
       ((SELECT id FROM book WHERE title = 'Вишнёвый сад'), 2),
       ((SELECT id FROM book WHERE title = 'Вишнёвый сад'), 4),
       ((SELECT id FROM book WHERE title = 'Мёртвые души'), 1),
       ((SELECT id FROM book WHERE title = 'Мёртвые души'), 3),
       ((SELECT id FROM book WHERE title = 'Мёртвые души'), 4);