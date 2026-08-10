# Scoops of Brainfreeze

> Deliberately Vulnerable Ice Cream Shop  
> Educational project for teaching **OWASP Top 10:2025**

**Public Repository** | Created for guest lecture at Jansons Institute of Technology (Coimbatore)

---

## Project Overview

**Scoops of Brainfreeze** is a fun, intentionally vulnerable web application themed as an ice cream shop.  
It is designed to help 3rd-year BE CSE students understand real-world security vulnerabilities in a relatable and memorable way.

### Tech Stack
- **Backend:** Spring Boot
- **Frontend:** React
- **Presentation:** Slidev (with live quiz addon)

---

## Current Status

| Deliverable | Status | Notes |
|-------------|--------|-------|
| Documentation | **Done** | Full architecture + OWASP Top 10 mapping with Mermaid |
| Domain Models | **Done** | Flavor, User, Order, Review |
| AuthController | **Done** | Weak authentication |
| FlavorController | **Done** | SQL Injection in search |
| OrderController | **Done** | IDOR + negative quantity |
| ReviewController | **Done** | Stored XSS (no sanitization) |
| TDD Tests | **Good coverage** | Tests documenting vulnerable behavior |
| Sample Data | **Done** | Flavors + users |
| React Frontend | Skeleton | Next major focus |
| Slidev Presentation | Started | Basic structure |

---

## Working Vulnerable Features

| OWASP | Feature | Endpoint |
|-------|---------|----------|
| A05 Injection | SQL Injection | `GET /api/flavors/search?q=` |
| A05 Injection | Stored XSS | `POST /api/reviews` |
| A01 Broken Access Control | IDOR | `GET/PUT /api/orders/{id}` |
| A07 Authentication Failures | Weak Login | `POST /api/auth/login` |
| A06 Insecure Design | Negative Quantity | `POST /api/orders` |
| A04 Cryptographic Failures | Plain text passwords | User storage |
| A02 Security Misconfiguration | Open CORS + verbose errors | Global |

---

## Default Credentials

- **Admin:** `admin` / `softserve123`
- **Student:** `student` / `password`

---

## How to Run

```bash
cd backend
./mvnw spring-boot:run
```

API base: `http://localhost:8080`

---

**Maintainer:** rajahpixelphone  
**Created:** August 2026
