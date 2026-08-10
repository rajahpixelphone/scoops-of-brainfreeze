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
- **Frontend:** React + Vite
- **Presentation:** Slidev (with live quiz addon)

---

## Current Status

| Component | Status |
|-----------|--------|
| Documentation | Done |
| Backend (Vulnerable APIs) | Done |
| React Frontend | **Done (basic pages)** |
| TDD Tests | Good coverage |
| Slidev Presentation | Started |

---

## Working Demo Features

| Page | Vulnerability Demonstrated |
|------|---------------------------|
| `/search` | SQL Injection |
| `/reviews` | Stored XSS |
| `/orders` | IDOR (Broken Access Control) |
| `/login` | Weak Authentication |

---

## How to Run

### Backend
```bash
cd backend
./mvnw spring-boot:run
```
→ http://localhost:8080

### Frontend
```bash
cd frontend
npm install
npm run dev
```
→ http://localhost:3000

### Default Credentials
- Admin: `admin` / `softserve123`
- Student: `student` / `password`

---

## Important Disclaimer

This application is **intentionally vulnerable**.  
Use only in controlled educational environments.

---

**Maintainer:** rajahpixelphone  
**Created:** August 2026
