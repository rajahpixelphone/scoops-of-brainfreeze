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
| Documentation | **Done** | Full architecture + OWASP Top 10 mapping with Mermaid diagrams |
| Backend Skeleton | **In Progress** | Spring Boot project + intentional misconfigurations |
| Frontend Skeleton | **In Progress** | React + Vite setup |
| Slidev Presentation | **Started** | Basic structure + quiz placeholders |
| Docker Support | **Started** | docker-compose.yml added |
| Full Vulnerable Features | Planned | Next phase |

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

## Repository Structure

```
scoops-of-brainfreeze/
├── backend/                 # Spring Boot application
├── frontend/                # React application
├── docs/                    # Full documentation + Mermaid diagrams
├── slides/                  # Slidev presentation
├── docker-compose.yml
└── README.md
```

---

## Development Approach

We follow **Test-Driven Development (TDD)** where practical, especially for:
- Backend business logic
- Security-related test cases (to demonstrate both vulnerable and fixed versions)

---

## License

Educational use only.  
Not intended for production use.

---

**Maintainer:** rajahpixelphone  
**Created:** August 2026
