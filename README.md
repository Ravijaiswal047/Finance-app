# Finance Dashboard Backend

## Overview

This project is a backend system for managing financial records with role-based access control. It supports user management, financial data operations, and dashboard analytics.

## Tech Stack

* Java
* Spring Boot
* Spring Security
* MySQL / H2
* JPA (Hibernate)
* Lombok

## Features

### User Management

* Create and manage users
* Assign roles (VIEWER, ANALYST, ADMIN)
* Active/inactive status

### Financial Records

* Create, update, delete records
* Filter by category, type, and date

### Dashboard APIs

* Total income
* Total expenses
* Net balance
* Category-wise totals

### Access Control

* Role-based authorization using Spring Security
* Viewer: Read only
* Analyst: Read + analytics
* Admin: Full access

### Validation & Error Handling

* Input validation using annotations
* Global exception handling
* Standardized error responses

## Authentication

* JWT-based authentication
* Token required for protected APIs

## API Endpoints

### Auth

POST /api/v1/auth/login

### Records

POST /api/v1/records
GET /api/v1/records
PUT /api/v1/records/{id}
DELETE /api/v1/records/{id}

### Dashboard

GET /api/v1/dashboard/summary

## How to Run

1. Clone repository
2. Configure database in application.properties
3. Run Spring Boot application
4. Use Postman to test APIs

## Assumptions

* Password encryption simplified for assignment
* JWT secret stored locally
* No refresh token implemented

## Future Improvements

* Password hashing (BCrypt)
* Pagination and sorting
* Redis caching
* API documentation (Swagger)
* Unit testing
