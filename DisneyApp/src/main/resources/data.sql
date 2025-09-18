-- ============================================
-- Inserción de géneros
-- ============================================
INSERT INTO genero (nombre) VALUES ('Animación');
INSERT INTO genero (nombre) VALUES ('Aventura');
INSERT INTO genero (nombre) VALUES ('Comedia');
INSERT INTO genero (nombre) VALUES ('Fantasía');

-- ============================================
-- Inserción de películas
-- ============================================
INSERT INTO pelicula (titulo, fecha_de_creacion, cod, calificacion)
VALUES ('Frozen', '2013-11-27', 'DIS001', 5);

INSERT INTO pelicula (titulo, fecha_de_creacion, cod, calificacion)
VALUES ('Moana', '2016-11-23', 'DIS002', 5);

INSERT INTO pelicula (titulo, fecha_de_creacion, cod, calificacion)
VALUES ('Toy Story', '1995-11-22', 'DIS003', 5);

INSERT INTO pelicula (titulo, fecha_de_creacion, cod, calificacion)
VALUES ('El Rey León', '1994-06-24', 'DIS004', 5);

INSERT INTO pelicula (titulo, fecha_de_creacion, cod, calificacion)
VALUES ('Aladdín', '1992-11-25', 'DIS005', 5);

-- ============================================
-- Inserción de personajes
-- ============================================
INSERT INTO personaje (cod, nombre, edad, peso, historia)
VALUES ('ANNA01', 'Anna', 18, '50', 'Princesa de Arendelle, hermana de Elsa');

INSERT INTO personaje (cod, nombre, edad, peso, historia)
VALUES ('ELSA01', 'Elsa', 21, '55', 'Reina de Arendelle con poderes de hielo');

INSERT INTO personaje (cod, nombre, edad, peso, historia)
VALUES ('MOANA01', 'Moana', 16, '48', 'Joven aventurera que busca salvar su isla');

INSERT INTO personaje (cod, nombre, edad, peso, historia)
VALUES ('MAUI01', 'Maui', 2000, '180', 'Semidiós que ayuda a Moana');

INSERT INTO personaje (cod, nombre, edad, peso, historia)
VALUES ('WOODY01', 'Woody', 10, '15', 'Vaquero de juguete y líder del grupo');

INSERT INTO personaje (cod, nombre, edad, peso, historia)
VALUES ('BUZZ01', 'Buzz Lightyear', 10, '18', 'Astronauta de juguete, amigo de Woody');

INSERT INTO personaje (cod, nombre, edad, peso, historia)
VALUES ('SIMBA01', 'Simba', 5, '80', 'León que será rey de la sabana');

INSERT INTO personaje (cod, nombre, edad, peso, historia)
VALUES ('MUFASA01', 'Mufasa', 40, '150', 'Padre de Simba, rey de la sabana');

INSERT INTO personaje (cod, nombre, edad, peso, historia)
VALUES ('ALADDIN01', 'Aladdín', 18, '60', 'Joven pobre que encuentra la lámpara mágica');

INSERT INTO personaje (cod, nombre, edad, peso, historia)
VALUES ('JASMINE01', 'Jasmín', 16, '50', 'Princesa de Agrabah, enamorada de Aladdín');

-- ============================================
-- Relación Película-Género (Many-to-Many)
-- ============================================
INSERT INTO peliculas_genero (id_pelicula, id_genero) VALUES (1, 1);
INSERT INTO peliculas_genero (id_pelicula, id_genero) VALUES (1, 4);

INSERT INTO peliculas_genero (id_pelicula, id_genero) VALUES (2, 1);
INSERT INTO peliculas_genero (id_pelicula, id_genero) VALUES (2, 2);

INSERT INTO peliculas_genero (id_pelicula, id_genero) VALUES (3, 1);
INSERT INTO peliculas_genero (id_pelicula, id_genero) VALUES (3, 3);

INSERT INTO peliculas_genero (id_pelicula, id_genero) VALUES (4, 1);
INSERT INTO peliculas_genero (id_pelicula, id_genero) VALUES (4, 2);

INSERT INTO peliculas_genero (id_pelicula, id_genero) VALUES (5, 1);
INSERT INTO peliculas_genero (id_pelicula, id_genero) VALUES (5, 4);

-- ============================================
-- Relación Personaje-Película (Many-to-Many)
-- ============================================
INSERT INTO personaje_pelicula (id_personaje, id_pelicula) VALUES (1, 1);
INSERT INTO personaje_pelicula (id_personaje, id_pelicula) VALUES (2, 1);

INSERT INTO personaje_pelicula (id_personaje, id_pelicula) VALUES (3, 2);
INSERT INTO personaje_pelicula (id_personaje, id_pelicula) VALUES (4, 2);

INSERT INTO personaje_pelicula (id_personaje, id_pelicula) VALUES (5, 3);
INSERT INTO personaje_pelicula (id_personaje, id_pelicula) VALUES (6, 3);

INSERT INTO personaje_pelicula (id_personaje, id_pelicula) VALUES (7, 4);
INSERT INTO personaje_pelicula (id_personaje, id_pelicula) VALUES (8, 4);

INSERT INTO personaje_pelicula (id_personaje, id_pelicula) VALUES (9, 5);
INSERT INTO personaje_pelicula (id_personaje, id_pelicula) VALUES (10, 5);
