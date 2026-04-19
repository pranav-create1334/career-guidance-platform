# career-guidance-platform
# 🚀 Smart Career Guidance & Job Matching Platform

## 📌 Overview

The **Smart Career Guidance & Job Matching Platform** is an AI-ready microservices-based system designed to help students and job seekers:

* Identify the right career path
* Understand required skills
* Get personalized job recommendations
* Follow a structured learning roadmap

This project solves a real-world problem where students often lack clarity about careers and job requirements.

---

## 🧩 Architecture

This project follows a **Microservices Architecture** using Spring Boot:

* **User Service** → Handles user authentication & profiles
* **API Gateway** → Single entry point for all client requests
* **Eureka Server** → Service discovery
* **(Upcoming)** Job Service, Recommendation Service, AI Module

---

## 🛠️ Tech Stack

* **Backend:** Spring Boot, Java
* **Architecture:** Microservices
* **Service Discovery:** Eureka Server
* **API Gateway:** Spring Cloud Gateway
* **Database:** MySQL (planned)
* **Build Tool:** Maven

---

## 📂 Project Structure

```
career-guidance-platform/
│
├── api-gateway/
├── user-service/
├── eureka-server/
└── README.md
```

---

## ⚙️ Features Implemented (So Far)

* ✅ Microservices setup
* ✅ API Gateway configuration
* ✅ Eureka Server setup
* ✅ User Service basic structure
* 🔄 Service communication (in progress)
* 🔄 Database integration (planned)

---

## 🚀 How to Run

### 1️⃣ Start Eureka Server

```bash
cd eureka-server
mvn spring-boot:run
```

### 2️⃣ Start User Service

```bash
cd user-service
mvn spring-boot:run
```

### 3️⃣ Start API Gateway

```bash
cd api-gateway
mvn spring-boot:run
```

---

## 🌐 Access Services

* Eureka Dashboard: http://localhost:8761
* API Gateway: http://localhost:8080

---

## 🎯 Future Enhancements

* 🔹 AI-based career recommendations
* 🔹 Skill-gap analysis
* 🔹 Resume builder integration
* 🔹 Job matching system
* 🔹 Real-time notifications

---

## 💡 Learning Goals

This project helps in understanding:

* Microservices architecture
* Spring Boot ecosystem
* API Gateway & Service Discovery
* Real-world scalable system design

---

## 🤝 Contribution

This is a personal learning project. Contributions and suggestions are welcome!

---

## 📜 License

This project is for educational purposes.
