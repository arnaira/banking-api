DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS persona;

create table persona (
id INT auto_increment primary key,
nombre VARCHAR(100) not null,
genero VARCHAR(1) not null,
edad INT check (edad >= 0),
dni VARCHAR(50) unique not null,
direccion VARCHAR(255),
telefono VARCHAR(20)
);



create table cliente (
id INT auto_increment primary key,
persona_id INT unique not null,
cliente_id VARCHAR (50)unique not null,
contrasena VARCHAR (255) not null,
foreign key (persona_id)
references persona(id)
);

ALTER table persona alter column id restart with 1;
INSERT INTO persona (nombre,genero,edad,dni,direccion,telefono) VALUES
	 ('Ana Rivera','F',27,'0801199908623','Otavalo sn y principal','098254785');