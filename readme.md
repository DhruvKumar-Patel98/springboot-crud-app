# Spring Boot CRUD Application (TDD Approach)

## Overview
This project is a Spring Boot-based CRUD application that manages authors and books using a relational database. The application follows the **Test-Driven Development (TDD) approach** and implements **DAO (Data Access Object) pattern** to interact with the database. It includes **unit tests and integration tests** to ensure functionality and reliability.

## Features
- CRUD operations for **Authors**
- CRUD operations for **Books**
- DAO pattern for data persistence
- Unit tests for business logic
- Integration tests for end-to-end validation
- Uses **Docker Compose** for PostgreSQL database setup

## Database Schema

### Authors Table
| Column | Type    | Description |
|--------|--------|-------------|
| id     | Long   | Primary Key |
| name   | String | Author Name |
| age    | Integer| Author Age  |

### Books Table
| Column    | Type   | Description          |
|-----------|--------|----------------------|
| isBn      | String | Primary Key (Book ID)|
| title     | String | Book Title           |
| author_id | Long   | Foreign Key (Author) |

## Technologies Used
- **Spring Boot** (REST API development)
- **PostgreSQL** (Database)
- **Docker & Docker Compose** (Containerization)
- **H2 Database** (In-memory testing database)
- **JUnit & Mockito** (Unit testing framework)
- **Spring Boot Test** (Integration testing)
- **Maven** (Build automation)

## Setup & Installation
1. Make sure **Docker** is installed and running.
2. Clone the repository:
   ```sh
   git clone https://github.com/yourusername/springboot-crud-app.git
   cd springboot-crud-app
   ```
3. Start PostgreSQL database using Docker Compose (Ensure that Docker Desktop is running):
   ```sh
   docker-compose up -d
   ```
4. Build the project:
   ```sh
   mvn clean install
   ```
5. Run the application:
   ```sh
   mvn spring-boot:run
   ```


## Running Tests
To execute unit and integration tests, run:
```sh
mvn test
```
