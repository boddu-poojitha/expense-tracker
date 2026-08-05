# 💰 Personal Finance & Expense Tracker

A secure RESTful Expense Tracker application built using **Java**, **Spring Boot**, **Spring Security**, **JWT Authentication**, **Spring Data JPA**, and **MySQL**.

The application allows users to register, authenticate using JWT tokens, manage expense categories, and record daily expenses through REST APIs.

---

# 🚀 Features

### 🔐 Authentication
- User Registration
- User Login
- JWT Token Generation
- Protected APIs using Spring Security
- Password Encryption with BCrypt

### 👤 User Management
- Create User
- View Users
- Update User
- Delete User

### 📂 Category Management
- Add Category
- View Categories
- Update Category
- Delete Category

### 💵 Expense Management
- Add Expense
- View All Expenses
- View Expense by ID
- Update Expense
- Delete Expense

### 🛡 Validation & Exception Handling
- Bean Validation
- Global Exception Handling
- Custom ResourceNotFoundException

---

# 🛠 Tech Stack

### Backend
- Java 21
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA
- Hibernate

### Database
- MySQL

### Build Tool
- Maven

### Testing
- Postman

---

# 📁 Project Structure

```text
src
└── main
    ├── java
    │   └── com.personalfinance.expense_tracker
    │       ├── config
    │       ├── controller
    │       ├── dto
    │       ├── entity
    │       ├── exception
    │       ├── mapper
    │       ├── repository
    │       ├── security
    │       ├── service
    │       └── service.impl
    └── resources
        └── application.properties
```

---

# 🗄 Database Schema

## Tables

- users
- category
- expense

## Relationships

```
User (1) -------- (*) Expense

Category (1) ---- (*) Expense
```

Each Expense belongs to one User and one Category.

---

# 🔑 Authentication Flow

1. Register a new user.
2. Login using email and password.
3. Receive a JWT Token.
4. Pass the token in the Authorization header.

```
Authorization: Bearer <your_token>
```

5. Access protected APIs.

---

# 📌 REST APIs

## Authentication

| Method | Endpoint |
|---------|-----------|
| POST | /api/users |
| POST | /api/auth/login |

---

## Users

| Method | Endpoint |
|---------|-----------|
| GET | /api/users |
| GET | /api/users/{id} |
| PUT | /api/users |
| DELETE | /api/users/{id} |

---

## Categories

| Method | Endpoint |
|---------|-----------|
| POST | /api/categories |
| GET | /api/categories |
| GET | /api/categories/{id} |
| PUT | /api/categories |
| DELETE | /api/categories/{id} |

---

## Expenses

| Method | Endpoint |
|---------|-----------|
| POST | /api/expenses |
| GET | /api/expenses |
| GET | /api/expenses/{id} |
| PUT | /api/expenses |
| DELETE | /api/expenses/{id} |

---

# 📋 Validation

The application validates incoming requests using Bean Validation.

Examples:

- Email cannot be empty
- Password cannot be empty
- Expense amount must be greater than zero
- Expense title is required
- Category name is required

---

# ⚠ Exception Handling

Custom exception handling has been implemented for:

- Resource Not Found (404)
- Validation Errors (400)
- Bad Requests
- Authentication Errors (401)

---

# 🧪 API Testing

All APIs were tested successfully using **Postman**.

Tested Modules:

- Authentication
- Users
- Categories
- Expenses

---

# 📈 Current Status

| Module | Status |
|---------|--------|
| Spring Boot Setup | ✅ |
| MySQL Integration | ✅ |
| JPA Relationships | ✅ |
| CRUD Operations | ✅ |
| DTO Layer | ✅ |
| Validation | ✅ |
| Exception Handling | ✅ |
| JWT Authentication | ✅ |
| Spring Security | ✅ |
| API Testing | ✅ |

---

# 🔄 Future Enhancements

- Expense Reports
- Monthly Analytics
- Dashboard APIs
- Pagination & Sorting
- Search & Filters
- Swagger/OpenAPI Documentation
- Docker Deployment
- Unit Testing (JUnit & Mockito)
- React Frontend
- Email Verification
- Password Reset

---

# 👩‍💻 Author

**Poojitha Boddu**

GitHub:
https://github.com/boddu-poojitha

---