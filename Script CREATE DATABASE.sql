CREATE DATABASE proyecto_integrador_java;

USE proyecto_integrador_java;

CREATE TABLE alumno(
legajo					INT NOT NULL AUTO_INCREMENT,
nombre					VARCHAR(500),
materias_aprobadas		VARCHAR(1000),
PRIMARY KEY (legajo)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

ALTER TABLE alumno AUTO_INCREMENT=11111;

CREATE TABLE materia(
indice					INT NOT NULL AUTO_INCREMENT, 
legajo					INTEGER,
correlativas			VARCHAR(1000),

PRIMARY KEY (indice)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

CREATE TABLE inscripcion(

indice					INT NOT NULL AUTO_INCREMENT,
materia					INTEGER,
legajo					INTEGER,
fecha					DATE,
PRIMARY KEY (indice)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

INSERT INTO alumno(nombre,materias_aprobadas) VALUES("Eduardo Dominguez","Fisica 1");

DELETE FROM alumno WHERE nombre='Eduardo Dominguez';

SELECT * FROM alumno;


