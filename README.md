# SMS Messaging Microservice

A Java microservice built with **Quarkus** that simulates an SMS messaging platform.

---

## Features

* **REST API Endpoints**
    * `POST /messages` — send a new SMS message
    * `GET  /messages` — list all messages
    * `GET  /messages/{id}` — retrieve a single message by ID
    * `GET  /messages/search?sourceNumber=...&destinationNumber=...` — search by phone numbers
    * `DELETE /messages/{id}` — delete a message by ID

* **Validation & Error Handling**
    * Bean Validation checks for source number, destination number, and message content (max 160 chars, numeric phone pattern).
    * Custom JSON error responses for validation errors.

* **Message Simulation**
    * Each message is initially stored as `PENDING` and immediately “delivered” or “failed” (random simulation) via an `SmsPublisher` component.

* **Persistence**
    * PostgreSQL database for storing and retrieving messages.

* **Documentation**
    * Built-in OpenAPI/Swagger UI available at `/q/swagger-ui`.

---

## Technology Stack

* Java 17+
* Quarkus
* PostgreSQL
* Maven
* Docker & Docker Compose
* JUnit 5 + RestAssured for testing

---

## Running the Service

### Prerequisites
* Java 17 or later
* Maven 3.8+
* Docker & Docker Compose

### Build & Start with Docker Compose

```bash
# Build the Quarkus JAR
mvn clean package -DskipTests

# Start PostgreSQL and the microservice
docker-compose up --build