--liquibase formatted sql

--changeset rinat-s:add persons

DROP TABLE IF EXISTS persons;
CREATE TABLE persons( id SERIAL, name VARCHAR(255), alco_type VARCHAR(255),  
                      soft_type VARCHAR (255), drink_id integer );

DROP TABLE IF EXISTS drinks;
CREATE TABLE drinks( id SERIAL, name VARCHAR(255), alco_type VARCHAR(255),  soft_type VARCHAR (255), 
                     location VARCHAR (255), is_available BOOLEAN DEFAULT TRUE);

INSERT INTO persons(name) 
VALUES 
('Ринат'), ('Евгений'), ('Аня'), ('Вика'), ('Антон'), ('Роман'), ('Мартын'),
('Полина'), ('Ира'), ('Слава'), ('Даша'), ('Сергей'), ('Ира'), ('Юра'), ('Кирилл');

INSERT INTO drinks(name, alco_type, soft_type, location)
VALUES
('Джин Тоник Обыкновенный', 'GIN', 'TONIC', 'Он лежит и ждет тебя в подвале'),
('Классическая водка с соком', 'VODKA', 'JUICE', 'Он лежит и ждет тебя в подвале'),
('Водка с колой', 'VODKA', 'COKE', 'Он лежит на улице') 
