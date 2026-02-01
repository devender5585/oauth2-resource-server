# OAuth2 Resource Server

A secure, stateless Resource Server responsible for protecting REST APIs
using JWT access tokens issued by a custom OAuth2 Authorization Server.
This project demonstrates **enterprise-grade API security** using Spring Boot
and Spring Security with strict token validation and role-based authorization.

---

## 🚀 Features

### 🔐 API Security
- OAuth2 Resource Server implementation using Spring Security
- JWT-based stateless authentication
- Token validation using Authorization Server JWK endpoint
- Strict Issuer (`iss`) and Audience (`aud`) validation
- Role-based access control using JWT claims

### 🧩 Code Quality & Architecture
- Clean separation from Authorization Server
- Modern lambda-based Spring Security configuration
- Centralized security configuration
- Clear API boundary definitions (public vs secure APIs)
- Incremental, commit-driven development

---

## 🧰 Tech Stack
- Java 17  
- Spring Boot 3.x  
- Spring Security 6.x  
- OAuth2 Resource Server  
- JWT (JSON Web Tokens)  
- Maven  

---

## 🏗️ Project Architecture

The Resource Server is designed as a **token consumer service**:

- **Security Configuration** – JWT validation, issuer & audience checks
- **Controller Layer** – Secured and public REST APIs
- **Authorization Layer** – Role-based access enforcement
- **Integration Layer** – JWK-based key retrieval from Authorization Server

This service contains **no authentication logic** and relies entirely on
validated JWT tokens for access control.

---

## 🔐 JWT Validation Flow

```
Client
  |
  | Authorization: Bearer <JWT>
  v
Resource Server
  |
  | Fetch JWK from Authorization Server
  | Validate signature
  | Validate issuer (iss)
  | Validate audience (aud)
  | Map roles to authorities
  v
Protected API
```

---

## 🔑 Security Configuration

### Public APIs
- Accessible without authentication

### Secured APIs
- Require a valid JWT
- Enforce role-based access (`ROLE_SERVICE`)

---

## 🔐 Example Secured Endpoints

| Method | Endpoint | Description |
|------|----------|-------------|
| GET | /api/public/hello | Public endpoint |
| GET | /api/secure/hello | Secured endpoint (JWT required) |
| GET | /api/secure/token-details | Decode JWT claims (demo purpose) |

---

## 🔐 Expected JWT Claims

The Resource Server expects JWT tokens containing:

```json
{
  "iss": "http://localhost:8080",
  "aud": ["resource-server"],
  "client_id": "client-app",
  "roles": ["SERVICE"],
  "scope": ["read"],
  "exp": 1710000000
}
```

---

## ▶️ How to Run

1. Clone the repository  
2. Ensure the Authorization Server is running  
3. Configure JWK endpoint in `application.properties`  

```properties
spring.security.oauth2.resourceserver.jwt.jwk-set-uri=http://localhost:8080/oauth2/jwks
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:8080
```

4. Run the application:

```bash
mvn spring-boot:run
```

Server runs at:
```
http://localhost:8081
```

---

## 🧠 Design Highlights
- Stateless API security using JWT
- No session or server-side authentication state
- Strict issuer and audience validation
- Role-based authorization using token claims
- Aligned with modern Spring Security best practices

---

## 📌 Future Enhancements
- Fine-grained permission-based access control
- API Gateway integration
- Centralized logging and monitoring
- Rate limiting and throttling
- OpenAPI / Swagger documentation

---

## 👨‍💻 Author Notes
This project represents a **production-ready OAuth2 Resource Server**
designed to work alongside a custom Authorization Server, following
clean architecture principles and real-world security practices.
