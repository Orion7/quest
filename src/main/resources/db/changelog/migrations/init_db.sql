--liquibase formatted sql

--changeset rinat-s:add persons

DROP TABLE IF EXISTS persons;
CREATE TABLE persons( id SERIAL, name VARCHAR(255), alco_type VARCHAR(255),
                      soft_type VARCHAR (255), drink_id integer );

DROP TABLE IF EXISTS drinks;
CREATE TABLE drinks( id SERIAL, name VARCHAR(255), alco_type VARCHAR(255),  soft_type VARCHAR (255),
                     location VARCHAR (255), is_available BOOLEAN DEFAULT TRUE);

DROP TABLE IF EXISTS drink_locations;
CREATE TABLE drink_locations( drink_id INTEGER, image VARCHAR(255));

INSERT INTO persons(name)
VALUES
('Ринат'), ('Евгений'), ('Аня'), ('Вика'), ('Антон'), ('Роман'), ('Мартын'),
('Полина'), ('Ира'), ('Слава'), ('Даша'), ('Сергей'), ('Ира'), ('Юра'), ('Кирилл');

INSERT INTO drinks(id, name, alco_type, soft_type, location)
VALUES
(1, 'Классическая водка с соком', 'VODKA', 'JUICE', ''),
(2, 'Биттер Водка', 'VODKA', 'TONIC', ''),
(3, 'Водка, не дай себе засохнуть', 'VODKA', 'SPRITE', ''),
(4, 'Джин Тоник Обыкновенный', 'GIN', 'TONIC', ''),
(5, 'Джин Тоник Необычный', 'GIN', 'SPRITE', ''),
(6, 'Джин Тоник Сочный', 'GIN', 'JUICE', ''),
(7, 'Виски Кола', 'WHISKEY', 'COKE', ''),
(8, 'Виски Кола Черри', 'WHISKEY', 'COKE', ''),
(9, 'Виски Кола Яблоко', 'WHISKEY', 'COKE', ''),
(10, 'Классический Ром Кола', 'RUM', 'COKE', ''),
(11, 'Сочный Ром', 'RUM', 'JUICE', ''),
(12, 'Римский Спрайт', 'RUM', 'SPRITE', ''),
(13, 'Пиздося aka Long Island', 'SECRET', 'SECRET', ''),
(14, 'Пиздося 2', 'SECRET', 'SECRET', ''),
(15, 'Пиздося 3', 'SECRET', 'SECRET', '');

-- Locations:
-- 1. За телевизором
-- 2. В верхнем шкафу
-- 3. В посудомойке
-- 4. В углу наверху кухни
-- 5. В гостевой комнате под кроватью
-- 6. В ванной
-- 7.
-- 8.
-- 9.
-- 10.
-- 11.
-- 12.
-- 13.
-- 14.
-- 15.

INSERT INTO drink_locations(drink_id, image)
VALUES
(1, ''),
(2, ''),
(3, ''),
(4, ''),
(5, ''),
(6, ''),
(7, ''),
(8, ''),
(9, ''),
(10, ''),
(11, ''),
(12, ''),
(13, ''),
(14, ''),
(15, '');
