#  Banking API - Microservicios Spring Boot 

Este proyecto consiste en la implementacion de dos microservicios independientes utilizando Java, Spring Boot y RabbitMQ, cumpliendo con una arquitectura basada en eventos, asincronica y desacoplada.

Ambos microservicios estan dockerizados y disenados para ejecutar operaciones CRUD, validaciones y comunicacion entre ellos mediante colas de mensajeria. 


---

## Tecnologias y Herramientas

- **Lenguaje:** Java 17
- **Frameworks y librerias:** Spring Boot, Spring Web, Spring Data JPA, Spring AMQP
- **Mensajeria:** RabbitMQ
- **ORM:** Hibernate + JPA
- **Documentacion:** Swagger OpenAPI
- **Base de Datos:** MySQL Server (local)
- **Contenedores:** Docker, Docker Compose
- **Testing:** JUnit 5, Mockito
- **Otras herramientas:** Maven

---

##  Estructura del Repositorio

ms-clients-person-service      # Microservicio para clientes
ms-accounts-movements-service  # Microservicio para cuentas y movimientos
docker-compose.yml             # Orquestador de contenedores
BaseDatos.sql                  # Script de creacion de tablas MySQL
arnaira.postman_collection     # Endpoints para pruebas locales en postman
README-es.md                   # Explicacion general del proyecto en espanol
README.md                      # Explicacion general del proyecto en ingles


---

##  Objetivo del Proyecto

- Permitir el registro, consulta, actualizacion y eliminacion de clientes.
- Registrar cuentas bancarias asociadas a clientes.
- Registrar movimientos bancarios y actualizar saldo automiticamente.
- Validar que el saldo sea suficiente antes de realizar un debito.
- Emitir un evento RabbitMQ al crear un cliente para simular comunicacion asincrona.
- Escuchar ese evento en otro microservicio y manejarlo asincronicamente.

---

## Pasos para ejecutar el proyecto

### 1. Clonar el repositorio

bash
git clone https://github.com/arnaira/banking-api.git


### 2. Asegurarse de tener MySQL Server corriendo

Una vez se tenga una instancia de MySQL server, se podra ejecutar:
bash
mysql -u root -p < BaseDatos.sql

Esto creara la base de datos arnaira con todas las tablas necesarias.


para poder ejecutarlo correctamente en los micros en el application-docker.properties debe existir esta configuracion:

spring.datasource.url=jdbc:mysql://host.docker.internal:3306/arnaira?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=contrase?a de su mysql server local
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver




### 3. Generar los .jar

Desde la raiz de cada microservicio:

bash
cd ms-clients-person-service
mvn clean package -DskipTests
cd ../ms-accounts-movements-service
mvn clean package -DskipTests


### 4. Construir las imagenes Docker por cada microservicio

Desde la raiz de cada microservicio:
bash
docker build -t ms-clients-person-service .
docker build -t ms-accounts-movements-service .


### 5. Levantar los contenedores

Desde la raiz de ambos microservicios donde se encuentra el archivo docker-compose.yml:
bash
docker-compose up --build


Esto levantara:

- RabbitMQ
- Microservicio de clientes (puerto 8081)
- Microservicio de cuentas (puerto 8082)

---

##  Endpoints principales

### Clientes

- POST /api/clientes: Crear cliente
- GET /api/clientes/{id}: Obtener por ID
- PUT /api/clientes/{id}: Actualizar
- DELETE /api/clientes/{id}: Eliminar

### Cuentas, Movimientos y Reporte

- POST /api/cuentas: Crear cuenta
- GET /api/cuentas/{id}: Obtener por ID
- PUT /api/cuentas/{id}: Actualizar cuentas
- POST /api/movimientos: Registrar movimiento
- GET /api/movimientos/cuenta/{numeroCuenta}: Filtrar por cuenta
- GET /api/movimientos/reportes?{numeroCuenta}&{fechaInicio}&{fechaFin}: Filtrar por fecha y cuenta
- PUT /api/movimientos/{id}: Actualizar movimientos

---

## Comunicacion asincrona con RabbitMQ

Al crear un nuevo cliente, se envia un mensaje a RabbitMQ.

mermaid
sequenceDiagram
    participant Cliente as ms-clients
    participant RabbitMQ
    participant Cuentas as ms-accounts

    Cliente->>RabbitMQ: Enviar mensaje con datos de nuevo cliente
    RabbitMQ->>Cuentas: Listener recibe y procesa mensaje


Esto permite simular una arquitectura basada en eventos.

---

## Swagger API Docs

Una vez los servicios esten corriendo, se podra visitar:

- **Clientes:** [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)
- **Cuentas:** [http://localhost:8082/swagger-ui/index.html](http://localhost:8082/swagger-ui/index.html)

---

## Pruebas unitarias

Microservicio clientes-persona tiene cobertura con pruebas unitarias usando:

- **JUnit 5**
- **Mockito**

Para ejecutar la prueba unitaria en la raiz del microservicio:
bash
mvn tests

La prueba unitaria que se realizo es para la entidad de dominio de cliente

Nota: se empezo a implementar una prueba de integracion en el mismo microservicio pero por cuestiones de tiempo no se pudo terminar, de igual forma esta incluida en el proyecto 

---

## Mejoras futuras

Mejoras que implementare a futuro:

-Implementare pruebas de integracion para ambos microservicios

-Incorporare seguridad con JWT u OAuth2 para proteccion de endpoints

-Automatizare la creacion de numero de cuenta al crear un cliente, utilizando el mensaje recibido en el listener desde RabbitMQ

-Uso de un contenedor Docker para MySQL, eliminando la dependencia de MySQL Server local

-Desarrollo de un frontend visual que consuma los endpoints y facilite la interaccion con el sistema

-Documentacion ampliada con diagramas de arquitectura, flujo de datos y anilisis de diseno

-Mejorare el manejo de errores con validaciones mas robustas y respuestas detalladas

-Ampliare el sistema para incluir funcionalidades como cierre de cuentas, reverso de movimientos y generacion de reportes financieros avanzados

---

## Contacto

Desarrollada por Ana Rivera
Email: [ana.rivera2023@gmail.com](mailto:ana.rivera2023@gmail.com)

--- 

## Otros Idiomas
[View this documentation in English](README.md)