# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

**StockQueryService** is a microservice within the StockManagement ecosystem—a Spring Boot 3.1 application handling product inventory, suppliers, customers, and orders. It's part of a broader microservices architecture that includes:

- `StockOperationService` (event producer)
- `UserAuthService` (authentication)
- `cloud_api_gw` (API Gateway, Spring Cloud Gateway)
- `eureka-server` (Service Discovery)

This service registers as a Eureka client, sits behind the API Gateway, and communicates via Kafka (bootstrap: `kafka:9092`, consumer group: `stock-op`).

## Architecture & Layers

**Package structure:**
- `config/` — Security (OAuth2 JWT), Swagger/OpenAPI, filters (JwtAuthenticationFilter, JwtAuthorizationFilter, GatewayIdentityHeaderFilter)
- `model/` — JPA entities (Product, Supplier, Order, OrderItem, Customer, User)
- `repository/` — Spring Data JPA repositories
- `service/` & `service/impl/` — Business logic (ProductService, OrderService, etc.)
- `controller/` — REST endpoints under `/api/v1/*`
- `application/dto/` — Data transfer objects
- `controller/handle/` — Global exception handling

**Security:** JWT-based (OAuth2 Resource Server). Expects `Authorization: Bearer <token>` header. All `/api/v1/**` endpoints require authentication. Swagger UI and Actuator are public.

**Database:** PostgreSQL. Uses Hibernate with JPA. Schema: `spring.jpa.hibernate.ddl-auto=update` (auto-evolving).

**Message broker:** Kafka for event-driven patterns.

## Build & Development

**Environment:**
- Java 17 (JDK 17, set via VS Code: Command Palette → "Java: Configure Java Runtime")
- Gradle 8+ (gradlew included)
- PostgreSQL (connection via env vars: `JDBC_DATABASE_URL`, `JDBC_DATABASE_USERNAME`, `JDBC_DATABASE_PASSWORD`)
- Eureka server (via `EUREKA_URL` env var)
- Kafka (via `kafka:9092` or override `spring.kafka.bootstrap-servers`)
- SonarQube (optional; configured for `http://localhost:9000`, org `unir`, project key `StockQueryService`)

**Common commands:**

```bash
# Build
./gradlew clean build --refresh-dependencies
./gradlew build

# Run locally (requires env vars set; see Config section)
./gradlew bootRun

# Tests
./gradlew test
./gradlew test --tests "com.maestria.springmvcstock.service.*"  # single test class/method

# Dependency management
./gradlew dependencies
./gradlew dependencyInsight --dependency <name>

# Documentation
./gradlew javadoc

# Quality gates
./gradlew sonarqube
```

**Docker:**

```bash
docker build -t stockqueryservice .
docker run -d -p 8003:8003 \
  -e JDBC_DATABASE_URL="jdbc:postgresql://..." \
  -e JDBC_DATABASE_USERNAME="..." \
  -e JDBC_DATABASE_PASSWORD="..." \
  -e EUREKA_URL="http://eureka-server:8761/eureka/" \
  -e SECURITY_JWT_SECRET="<base64-secret>" \
  -e SECRET_KEY_ENV="<key>" \
  --name stockqueryservice stockqueryservice
```

Service runs on **port 8003**.

## Configuration & Secrets

**Environment variables** (set before `./gradlew bootRun` or in Docker):

| Variable | Purpose | Example |
|----------|---------|---------|
| `JDBC_DATABASE_URL` | PostgreSQL connection | `jdbc:postgresql://localhost:5432/stock` |
| `JDBC_DATABASE_USERNAME` | DB user | `postgres` |
| `JDBC_DATABASE_PASSWORD` | DB password | — |
| `EUREKA_URL` | Eureka service URL | `http://localhost:8761/eureka/` |
| `SECURITY_JWT_SECRET` | JWT signing key (base64) | — |
| `SECRET_KEY_ENV` | General secret key | — |

**Development**: For local testing without external services, set up a test PostgreSQL instance locally or use H2 (in-memory). The `build.gradle` includes H2 for testing.

## REST API Endpoints

Swagger UI available at `http://localhost:8003/swagger-ui.html` (after auth or if you add permits for GET endpoints).

**Core resources:**

- `GET/POST/PUT/DELETE /api/v1/products` — Product CRUD
- `GET/POST/PUT/DELETE /api/v1/orders` — Order CRUD
- `GET/POST/PUT/DELETE /api/v1/customers` — Customer CRUD
- `GET/POST/PUT/DELETE /api/v1/suppliers` — Supplier CRUD

All require authentication except Swagger UI and `/actuator/**`.

## Known Issues & Debt

1. **Entity mapping bug**: `Order` entity has `@Table(name = "users")` which conflicts with the `User` entity. Should be `@Table(name = "orders")`. This will cause data corruption if both entities are used simultaneously.

2. **Missing Kafka dependency**: `build.gradle` does not include `spring-kafka` starter, but `application.properties` configures Kafka. Add `implementation 'org.springframework.boot:spring-boot-starter-kafka'` to dependencies.

3. **Field injection bug**: `CustomerController` has `private CustomerService customerService` without `@Autowired` or constructor injection via `@RequiredArgsConstructor`. This will cause NullPointerException at runtime. Use constructor injection or add `@Autowired`.

4. **No Docker Compose**: The ecosystem lacks a `docker-compose.yml` for local orchestration. Consider creating one for development (Postgres, Eureka, Kafka, API Gateway, services).

## Testing & Verification

- Run all tests: `./gradlew test`
- Run a single test: `./gradlew test --tests "com.maestria.springmvcstock.service.impl.ProductServiceImpTest"`
- Test with real DB: Set up PostgreSQL locally and point `JDBC_DATABASE_URL` there.
- Manual API testing: Use Swagger UI (after auth) or tools like `curl`/Postman. Example:

```bash
curl -H "Authorization: Bearer <jwt-token>" \
  http://localhost:8003/api/v1/products
```

## SonarQube Integration

The project is configured for SonarQube analysis (org `unir`, project key `StockQueryService`). Run quality checks locally:

```bash
./gradlew sonarqube
# Then visit http://localhost:9000 and look for this project
```

## Deployment Notes

- The service runs behind an API Gateway (`cloud_api_gw`). CORS is managed there, not locally.
- Eureka registration is mandatory (`@EnableDiscoveryClient`). Ensure Eureka server is reachable.
- Kafka is used for inter-service events. Ensure Kafka broker is running before starting this service.
- Use Blue-Green or Rolling deployments to minimize downtime.


Use spring-expert 