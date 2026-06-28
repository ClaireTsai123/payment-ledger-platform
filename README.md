# Payment Ledger Platform

Banking Transaction and Ledger Platform is a Java 17, Spring Boot 3.5, Maven multi-module skeleton for a microservice-based banking system.

## Modules

- `common`: shared enums, DTO package, event package, exception package, and future shared contracts.
- `api-gateway`: Spring Cloud Gateway entry point for routing client traffic.
- `auth-service`: planned owner of JWT authentication and identity flows.
- `account-service`: planned owner of accounts, account status, and balances.
- `payment-service`: planned orchestrator for the payment Saga.
- `ledger-service`: planned owner of double-entry ledger records.
- `notification-service`: planned consumer of payment events for customer and operational notifications.
- `audit-service`: planned recorder of business audit events.

Each runnable service is an independent Spring Boot application with its own `application.yml`, Dockerfile, and Actuator health endpoint at `/actuator/health`.

## Architecture

The platform is organized around service ownership boundaries:

- API Gateway routes external requests to internal services.
- Auth Service handles JWT authentication.
- Account Service manages accounts and balances.
- Payment Service orchestrates the payment Saga across account, ledger, notification, and audit boundaries.
- Ledger Service records double-entry ledger entries.
- Notification Service consumes payment events.
- Audit Service records business audit events.
- Common stores shared enums, DTOs, events, and exceptions.

Kafka is included in single-node KRaft mode as infrastructure for future asynchronous integration. Redis is included for future caching, token, idempotency, or coordination use cases. No authentication logic, controllers, entities, message producers, consumers, or business workflows are implemented yet.

## Planned Business Flow

The intended future flow is:

1. A client calls API Gateway.
2. API Gateway routes authentication requests to Auth Service and banking requests to the correct domain service.
3. Auth Service issues and validates JWTs.
4. Payment Service receives payment commands and starts a Saga.
5. Account Service performs account and balance checks when payment orchestration is implemented.
6. Ledger Service records balanced debit and credit entries.
7. Notification Service reacts to payment events.
8. Audit Service records business audit events for traceability.

This repository currently contains only the clean project skeleton. Payment logic, account logic, ledger logic, notification logic, audit logic, controllers, entities, repositories, and message handlers are intentionally left out.

## Local Infrastructure

Start PostgreSQL databases, Kafka in KRaft mode, and Redis:

```bash
docker compose up -d
```

Service ports:

- `api-gateway`: `8080`
- `auth-service`: `8081`
- `account-service`: `8082`
- `payment-service`: `8083`
- `ledger-service`: `8084`
- `notification-service`: `8085`
- `audit-service`: `8086`

Database ports:

- `auth_db`: `localhost:5432`
- `account_db`: `localhost:5433`
- `payment_db`: `localhost:5434`
- `ledger_db`: `localhost:5435`
- `notification_db`: `localhost:5436`
- `audit_db`: `localhost:5437`

Kafka and Redis:

- Kafka bootstrap server: `localhost:9092`
- Redis: `localhost:6379`

## Build

Run all module tests:

```bash
./mvnw clean test
```
