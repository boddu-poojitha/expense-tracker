# 💰 Expense Tracker

A RESTful Expense Tracker application built using **Java**, **Spring Boot**, **Spring Data JPA**, and **MySQL**. This project helps users manage their personal expenses by organizing them into categories and storing expense records efficiently.

## 🚀 Features

- User Management (CRUD)
- Category Management (CRUD)
- Expense Management (CRUD)
- DTO Layer
- Input Validation
- Global Exception Handling
- REST API Architecture
- MySQL Database Integration

## 🛠️ Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven

## 📁 Project Structure

```
src/main/java
├── controller
├── dto
├── entity
├── exception
├── repository
├── service
└── service/impl
```

## 🗄️ Database

Database: **MySQL**

Tables:
- User
- Category
- Expense

Relationships:
- One User → Many Expenses
- One Category → Many Expenses
- One Expense belongs to one User
- One Expense belongs to one Category

## 📌 REST APIs

### User APIs

- POST `/api/users`
- GET `/api/users`
- GET `/api/users/{id}`
- PUT `/api/users`
- DELETE `/api/users/{id}`

### Category APIs

- POST `/api/categories`
- GET `/api/categories`
- GET `/api/categories/{id}`
- PUT `/api/categories`
- DELETE `/api/categories/{id}`

### Expense APIs

- POST `/api/expenses`
- GET `/api/expenses`
- GET `/api/expenses/{id}`
- PUT `/api/expenses`
- DELETE `/api/expenses/{id}`

## 📋 Current Progress

✅ Spring Boot Project Setup

✅ MySQL Integration

✅ Entity Relationships

✅ Repository Layer

✅ Service Layer

✅ Controller Layer

✅ DTO Implementation

✅ Validation

✅ Global Exception Handling

## 🔄 Upcoming Features

- Spring Security & JWT Authentication
- Password Encryption
- User Login & Registration
- Swagger/OpenAPI Documentation
- Unit Testing (JUnit & Mockito)
- Docker Support
- Deployment
- React Frontend

## 👩‍💻 Author

**Poojitha Boddu**

GitHub: https://github.com/boddu-poojitha