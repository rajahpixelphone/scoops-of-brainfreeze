# Scoops of Brainfreeze

> Deliberately Vulnerable Ice Cream Shop  
> Educational project for teaching **OWASP Top 10:2025**

**Public Repository** | Guest Lecture – Jansons Institute of Technology

---

## Project Status: Ready for Lecture

| Component | Status |
|-----------|--------|
| Documentation | ✅ Done |
| Backend (Vulnerable APIs) | ✅ Done |
| React Frontend | ✅ Done |
| TDD Tests | ✅ Good coverage |
| Slidev Presentation | ✅ Expanded |

---

## How to Run the Full Demo

### 1. Backend
```bash
cd backend
./mvnw spring-boot:run
```
→ http://localhost:8080

### 2. Frontend
```bash
cd frontend
npm install
npm run dev
```
→ http://localhost:3000

### 3. Presentation (Slidev)
```bash
cd slides
npm install
npm run dev
```

---

## Default Credentials

- **Admin:** `admin` / `softserve123`
- **Student:** `student` / `password`

---

## Demo Features Available

| Page | Vulnerability |
|------|---------------|
| Search | SQL Injection |
| Reviews | Stored XSS |
| Orders | IDOR |
| Login | Weak Authentication |

---

## Important Disclaimer

This application is **intentionally vulnerable**.  
Use only in controlled educational environments.

---

**Maintainer:** rajahpixelphone  
**Created:** August 2026
