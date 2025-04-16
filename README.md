# E-Commerce Microservices System

This repository contains a microservices-based e-commerce backend system consisting of the following services:

- **common-lib**: Shared models and utilities used by other services.
- **user-service**: Handles user registration and login.
- **order-service**: Allows users to place orders and publishes events to Kafka.
- **inventory-service**: Listens to Kafka and updates product inventory.

Each service is Dockerized and communicates over REST and Kafka topics.

---

## 🧱 Technologies

- **Java 17 + Spring Boot**
- **Apache Kafka**
- **Docker**
- **MariaDB**
- **Maven**
- **Lombok**
- **Gson**

---
## 🚀 How to Run

### 1. Clone the Repository

```bash
  git clone https://github.com/yourusername/e-orderhub.git
  cd e-orderhub
```

