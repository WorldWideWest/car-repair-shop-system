DROP DATABASE IF EXISTS car_repair;
CREATE DATABASE IF NOT EXISTS car_repair;

USE car_repair;

-- Tables for the webapp functionality

DROP TABLE IF EXISTS Ticket;
CREATE TABLE IF NOT EXISTS Ticket(
	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    first_name VARCHAR(200) NOT NULL,
    last_name VARCHAR(200) NOT NULL,
    vehicle_name VARCHAR(500) NOT NULL,
    registration_number VARCHAR(50) NOT NULL,
    repair_type VARCHAR(1000) NOT NULL
)ENGINE=INNODB;

DROP TABLE IF EXISTS `Status`;
CREATE TABLE IF NOT EXISTS `Status`(
	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    ticket INT,
    `status` VARCHAR(20),
    `description` VARCHAR(2000),
    cost DOUBLE,
    FOREIGN KEY(ticket) REFERENCES Ticket(id)
)ENGINE=INNODB;


-- Tables for authentication
USE car_repair;

DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users(
	username VARCHAR(50) NOT NULL PRIMARY KEY,
    `password` VARCHAR(50) NOT NULL,
    enabled TINYINT(1) NOT NULL
)ENGINE=INNODB;

INSERT INTO users VALUES
('dzenan.dzafic', '{noop}eminem662', 1);


DROP TABLE IF EXISTS authorities;
CREATE TABLE IF NOT EXISTS authorities(
	username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    
    UNIQUE KEY `authorities_idx_1` (`username`, `authority`),    
    CONSTRAINT  `authorities_ibfk_1` FOREIGN KEY (username) REFERENCES users(username)
);

INSERT INTO authorities VALUES
('dzenan.dzafic', 'ROLE_EMPLOYEE');



