create database if not exists arnaira;
use arnaira;

------------------- Micro clients-person-----------------------------------------------
-- Table persona (principal entity of micro clients-person)
create table persona (
id INT auto_increment primary key,
nombre VARCHAR(100) not null,
genero VARCHAR(1) not null,
edad INT check (edad >= 0),
dni VARCHAR(50) unique not null,
direccion VARCHAR(255),
telefono VARCHAR(20)
);

INSERT INTO persona (nombre,genero,edad,dni,direccion,telefono) VALUES
	 ('Jose Lema','M',26,'0801199908623','Otavalo sn y principal','098254785'),
	 ('Marianela Montalvo','F',27,'0801199808623','Amazonas y NNUU','097548965'),
	 ('Juan Osorio','M',27,'0801199808999','13 junio y Equinoccial','099974587');
	
-- Table cliente (1:1 with persona)
create table cliente (
id INT auto_increment primary key,
persona_id INT unique not null,
cliente_id VARCHAR (50)unique not null,
contrasena VARCHAR (255) not null,
foreign key (persona_id)
references persona(id)
);

------------------- Micro accounts-movements-----------------------------------------------
-- Table cuenta (principal entity of micro accounts-movements)
create table cuenta (
id INT auto_increment primary key,
numero_cuenta VARCHAR (30) unique not null,
tipo_cuenta VARCHAR(3),-- AHO,CHQ
saldo_inicial DECIMAL(15,2) default 0.0,
estado boolean default true,
cliente_id VARCHAR (50) not null
);
-- Table movimiento (1:N with cuenta)
create table movimiento (
id INT auto_increment primary key,
cuenta_id INT not null,
numero_cuenta VARCHAR (30) not null,
fecha DATE,
tipo_movimiento VARCHAR(3) not null,-- DEB, CRE
monto DECIMAL (15,2) not null,
saldo DECIMAL (15,2) not null,
foreign key (cuenta_id)
references cuenta(id)
);


