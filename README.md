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

## Current Status (Updated)

| Deliverable | Status | Notes |
|-------------|--------|-------|
| Documentation | **Done** | Full architecture + OWASP Top 10 mapping with Mermaid diagrams |
| Domain Models | **Done** | Flavor, User, Order |
| Repositories | **Done** | Including intentional vulnerable queries |
| AuthController | **Done** | Weak authentication (plain text passwords, no rate limiting) |
| FlavorController | **Done** | SQL Injection in search |
| OrderController | **Done** | IDOR + negative quantity business logic flaw |
| TDD Tests | **In Progress** | Tests documenting vulnerable behavior |
| Sample Data | **Done** | Flavors + admin/student users |
| React Frontend | Skeleton | Pending pages |
| Slidev Presentation | Started | Basic structure |

---

## Default Credentials (for demos)

- **Admin:** `admin` / `softserve123`
- **Student:** `student` / `password`

---

## Important Disclaimer

This application is **intentionally vulnerable**.  
It is created purely for educational purposes.  

**Do not** deploy it on the public internet without proper isolation and access controls.

---

## Quick Links

- [Architecture & Vulnerabilities Documentation](docs/ARCHITECTURE_AND_VULNERABILITIES.md)
- [Slidev Presentation](slides/slides.md)

---

## How to Run (Backend)

```bash
cd backend
./mvnw spring-boot:run
```

API will be available at `http://localhost:8080`

---

**Maintainer:** rajahpixelphone  
**Created:** August 2026
