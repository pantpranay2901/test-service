# Service Name

Short description of what this service does.

## Prerequisites

1. Java 17+
2. Maven Wrapper (`mvnw`) included in repo

## Run

### Dev

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

### Prod

```bash
DB_URL=jdbc:postgresql://host:5432/mydb DB_USERNAME=postgres DB_PASSWORD=secret ./mvnw spring-boot:run -Dspring-boot.run.profiles=prod
```

## API Docs

1. Swagger UI (dev): `http://localhost:8080/swagger-ui/index.html`
2. OpenAPI JSON (dev): `http://localhost:8080/v3/api-docs`

## Health and Ops

1. Health: `http://localhost:8080/api/v1/health`
2. Actuator health: `http://localhost:8080/actuator/health`
3. Actuator info: `http://localhost:8080/actuator/info`

## Test and Coverage

```bash
./mvnw test
./mvnw test jacoco:report
```

Coverage report: `target/site/jacoco/index.html`

## Environment Variables (Prod)

1. `DB_URL`
2. `DB_USERNAME`
3. `DB_PASSWORD`

## Configuration Files

1. `src/main/resources/application.properties`
2. `src/main/resources/application-dev.properties`
3. `src/main/resources/application-test.properties`
4. `src/main/resources/application-prod.properties`
5. `src/main/resources/logback-spring.xml`
