# WebFlux R2DBC CRUD

A reactive CRUD application built using **Spring WebFlux**, **R2DBC**, and **PostgreSQL**.  
It demonstrates a clean implementation of CRUD operations with pagination, DTO mapping, exception handling, and manual entity relations (Many-to-One).

---

## 🚀 Features

- Reactive REST APIs with Spring WebFlux
- Reactive PostgreSQL access using R2DBC
- Declarative CRUD with `ReactiveCrudRepository`
- DTO mapping (e.g., Book + Author)
- Global exception handling
- Pagination with `DatabaseClient`
- Criteria-style dynamic search query

---

## 🛠️ Project Setup

### ✅ Prerequisites

- Java 17+ or Java 21
- Maven
- PostgreSQL
- IDE like IntelliJ or VS Code

---

## ⚙️ Configuration

### 1. Application Properties

Update `src/main/resources/application.yml`:

```yaml
spring:
  r2dbc:
    url: r2dbc:postgresql://localhost:5432/webflux_crud
    username: your_db_username
    password: your_db_password

  sql:
    init:
      mode: always

  datasource:
    driver-class-name: org.postgresql.Driver

logging:
  level:
    org.springframework.r2dbc: DEBUG




PostgreSQL DB Setup:

CREATE DATABASE webflux_crud;

-- Create 'authors' table
CREATE TABLE authors (
    id SERIAL PRIMARY KEY,
    author_name VARCHAR(100) NOT NULL
);

-- Create 'books' table
CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    book_name VARCHAR(100) NOT NULL,
    publish_date DATE,
    author_id BIGINT REFERENCES authors(id)
);


▶️ Running the Application:

# Build
mvn clean install

# Run
mvn spring-boot:run

📦 Tech Stack
Spring Boot 3.x

Spring WebFlux

Spring Data R2DBC

PostgreSQL

Project Reactor

Maven

