DROP DATABASE IF EXISTS `615600`;
CREATE DATABASE `615600`; 
USE `615600`; 

CREATE TABLE utente (
	ID INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE categoria (
	nome VARCHAR(255) PRIMARY KEY
);

CREATE TABLE attivita (
	ID INT AUTO_INCREMENT PRIMARY KEY,
    utente INT NOT NULL,
    titolo VARCHAR(255) NOT NULL,
    categoria VARCHAR(255) NOT NULL,
    contenuto VARCHAR(255) NOT NULL DEFAULT '',
    FOREIGN KEY (utente) REFERENCES utente(ID),
    FOREIGN KEY (categoria) REFERENCES categoria(nome)
 );


INSERT INTO utente(username, password)
VALUES('simone', '$2a$10$ZNaqHvEIOJr3FxrvOzt1de6RxOW6YhyFbl8kWqF2rlWFIBZDoMN0q');

INSERT INTO categoria
VALUES ('Lavoro'), ('Casa'), ('Famiglia'), ('Salute'), ('Finanze');

INSERT INTO attivita(utente, titolo, categoria, contenuto)
VALUES (1, 'Fare la lavatrice', 'Casa', 'Lava la felpa e i pantaloni'), (1, 'Progetto', 'Lavoro', 'Finisci il progetto per Capodanno');