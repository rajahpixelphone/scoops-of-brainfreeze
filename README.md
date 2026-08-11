# Scoops of Brainfreeze

> Deliberately Vulnerable Ice Cream Shop  
> Educational project for teaching **OWASP Top 10:2025**

**Public Repository** | Guest Lecture – Jansons Institute of Technology

---

## Status: Lecture Ready ✅

| Component | Status |
|-----------|--------|
| Documentation | ✅ Updated |
| Backend (Vulnerable APIs) | ✅ Complete |
| React Frontend | ✅ Complete |
| TDD Tests | ✅ Present |
| Slidev + Interactive Quizzes | ✅ Proper layout syntax |
| Admin Panel (no auth) | ✅ Added |
| File Upload (A08) | ✅ Added |
| Zoho Catalyst (Option A) | ✅ Supported |

---

## How to Run (Local Development)

### Backend
```bash
cd backend
./mvnw spring-boot:run
```
→ http://localhost:8080

### Frontend (separate)
```bash
cd frontend
npm install
npm run dev
```
→ http://localhost:3000

### Presentation
```bash
cd slides
npm install
npm run dev
```

---

## Deploy on Zoho Catalyst (Option A – Recommended)

Embed the React frontend inside Spring Boot and deploy as a single AppSail service.

**One-command build:**

```bash
chmod +x build-for-catalyst.sh
./build-for-catalyst.sh
```

Then:

```bash
cd backend
catalyst deploy
```

Full guide: [docs/CATALYST_DEPLOYMENT.md](docs/CATALYST_DEPLOYMENT.md)

---

## Default Credentials

- **Admin:** `admin` / `softserve123`
- **Student:** `student` / `password`

---

## Key Demo Endpoints

| Feature | Endpoint / Page | Vulnerability |
|---------|-----------------|---------------|
| Search | `/search` | SQL Injection |
| Reviews | `/reviews` | Stored XSS |
| Orders | `/orders` | IDOR |
| Login | `/login` | Weak Auth |
| Admin | `GET /api/admin/users` | Broken Access Control |
| Upload | `POST /api/upload` | Insecure File Upload |

---

## Important Disclaimer

This application is **intentionally vulnerable**.  
Use only in controlled educational environments.  
Do **not** leave a public Catalyst URL open permanently.

---

**Maintainer:** rajahpixelphone  
**Created:** August 2026
