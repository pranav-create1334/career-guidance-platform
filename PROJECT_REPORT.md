# Smart Career Guidance System - Project Report

## Overview
This is a microservices-based architecture implementing a Smart Career Guidance System using Spring Cloud, featuring service discovery, API gateway routing, and user management with JWT authentication.

---

## eureka-server

**Primary Purpose:**  
Eureka Server acts as a service registry and discovery mechanism for the microservices architecture. It enables all services to register themselves and discover other services dynamically.

**Key Functionalities:**
- Service registration and discovery
- Health monitoring of registered services
- Load balancing support for service-to-service communication

**Implemented Code Components:**
- `EurekaServerApplication.java` - Main Spring Boot application with `@EnableEurekaServer` annotation to enable the Eureka Server functionality
- Spring Cloud Netflix Eureka Server dependency for service discovery infrastructure

**Data/Configuration Implemented:**
- **Port:** 8761 (standard Eureka Server port)
- **Server Configuration:**
  - `eureka.client.register-with-eureka=false` - Server does not register with itself
  - `eureka.client.fetch-registry=false` - Server does not fetch registry from others
- **Dependencies:** Spring Boot 3.2.5, Spring Cloud 2023.0.1, Java 17
- **Architecture Pattern:** Service Registry Pattern (Server-side Service Discovery)

---

## user-service

**Primary Purpose:**  
User Service manages all user-related operations including user registration, authentication (login), and user profile management. It serves as the core authentication and user management microservice for the platform.

**Key Functionalities:**
- User registration with validation
- User login with JWT token generation
- Retrieve user information (by ID and all users)
- Role-based access control
- Password encryption using BCrypt
- JWT-based authentication and authorization

**Implemented Code Components:**

- **Entity Layer:**
  - `User.java` - Core user entity with fields: id, name, email, password, role with validation annotations (@Email, @NotBlank)

- **Data Transfer Objects:**
  - `LoginRequest.java` - Request DTO with email and password fields
  - `LoginResponse.java` - Response DTO returning user info and JWT token
  - `UserDTO.java` - User data transfer object for API responses

- **Controller Layer:**
  - `UserController.java` - REST endpoints:
    - `POST /users/register` - Register new user
    - `POST /users/login` - Authenticate and generate JWT token
    - `GET /users/{id}` - Get user by ID
    - `GET /users/all` - Retrieve all users

- **Security Layer:**
  - `SecurityConfig.java` - Spring Security configuration with:
    - JWT filter integration (`JwtFilter`)
    - BCrypt password encoder bean
    - Custom `UserDetailsServiceImpl` for loading user credentials
    - Security filter chain with CSRF disabled
    - Public access to `/users/login` and `/users/register`, authenticated access for other endpoints
  - `JwtUtil.java` - JWT token generation and validation utilities

- **Service Layer:**
  - `UserService.java` - Business logic for user operations (registration, authentication, retrieval)

- **Repository Layer:**
  - User repository (JPA) for database operations

**Data/Configuration Implemented:**
- **Port:** 8081
- **Database:** MySQL
  - URL: `jdbc:mysql://localhost:3306/career_guidance_db`
  - Credentials: root / root123
  - Driver: MySQL JDBC Connector
- **Persistence Configuration:**
  - `spring.jpa.hibernate.ddl-auto=update` - Auto-update schema on startup
  - `spring.jpa.show-sql=true` - Log SQL queries
  - Hibernate Dialect: MySQL 8+
- **Eureka Integration:** Registered with Eureka Server at `http://localhost:8761/eureka`
- **Dependencies:** Spring Boot Web, Spring Data JPA, MySQL Connector, Spring Security, JWT (io.jsonwebtoken), Spring Cloud Eureka Client, Validation, Java 17
- **Table:** `users` table with auto-increment ID

---

## api-gateway

**Primary Purpose:**  
API Gateway serves as the single entry point for all client requests to the microservices ecosystem. It handles request routing, load balancing, and provides a unified API interface.

**Key Functionalities:**
- Request routing to microservices based on URL paths
- Load-balanced routing using Eureka service discovery
- Request/response filtering and transformation capabilities
- Service discovery integration with Eureka

**Implemented Code Components:**
- `ApiGatewayApplication.java` - Main Spring Boot application with:
  - `@EnableDiscoveryClient` - Enables Eureka client registration and service discovery
  - Bootstraps Spring Cloud Gateway framework

- Spring Cloud Gateway dependency for route configuration and request proxying

**Data/Configuration Implemented:**
- **Port:** 8080 (main entry point)
- **Route Configuration:**
  - Route ID: `user-service`
  - URI: `lb://user-service` (load-balanced routing to user-service)
  - Path Predicate: `/users/**` (routes requests matching /users/* to user-service)
- **Eureka Integration:** Registered as discovery client with Eureka Server at `http://localhost:8761/eureka`
- **Dependencies:** Spring Boot 3.2.5, Spring Cloud Gateway, Spring Cloud Eureka Client, Caffeine Cache, Java 21
- **Architecture Pattern:** API Gateway Pattern with load-balanced service discovery

---

## System Architecture Summary

| Component | Port | Role | Key Technology |
|-----------|------|------|-----------------|
| **Eureka Server** | 8761 | Service Registry & Discovery | Spring Cloud Netflix Eureka |
| **User Service** | 8081 | User Management & Authentication | Spring Boot, MySQL, JWT, Spring Security |
| **API Gateway** | 8080 | Request Router & Load Balancer | Spring Cloud Gateway |

**Communication Flow:**
1. Client requests → API Gateway (port 8080)
2. Gateway routes `/users/**` requests → User Service (port 8081) via load-balanced discovery
3. All services register with Eureka Server (port 8761) for dynamic service discovery

**Technology Stack:**
- **Framework:** Spring Boot 3.2.5
- **Cloud Infrastructure:** Spring Cloud 2023.0.1
- **Service Discovery:** Netflix Eureka
- **API Gateway:** Spring Cloud Gateway
- **Database:** MySQL
- **Authentication:** JWT + Spring Security + BCrypt
- **Java Version:** 17-21
- **Build Tool:** Maven

