---
theme: default
title: Scoops of Brainfreeze
addons:
  - slidev-addon-slide-quiz
slideQuiz:
  quizGroupId: scoops-brainfreeze-jit
  # After running `npx create-slide-quiz` or configuring AnyCable, set:
  # wsUrl: wss://your-cable.anycable.io/cable
  # quizUrl: /quiz.html
---

# 🍦 Scoops of Brainfreeze

### Teaching OWASP Top 10:2025 with Ice Cream

**Guest Lecture**  
Jansons Institute of Technology  
3rd Year BE CSE

---

# About Today

- Duration: ~60 minutes
- Style: Live demos + interactive quizzes
- Goal: Understand real vulnerabilities through a fun application

---

# Agenda

1. Why this application?
2. Quick architecture tour
3. Live attacks on Scoops of Brainfreeze
4. Interactive quizzes
5. How to fix the issues
6. Key takeaways + Q&A

---

# Why an Ice Cream Shop?

- Highly relatable
- Naturally funny
- Makes vulnerabilities memorable

> Students remember  
> “SQL Injection while searching for Chocolate Chip”  
> much better than abstract theory.

---

# The Application

**Scoops of Brainfreeze**

- Frontend: React
- Backend: Spring Boot
- Intentionally vulnerable by design

Repo: https://github.com/rajahpixelphone/scoops-of-brainfreeze

---

# Architecture

```mermaid
flowchart LR
    A[React Frontend] --> B[Spring Boot API]
    B --> C[(H2 Database)]
```

Simple. Clear. Perfect for demos.

---

# Live Demo Time 🔥

We will now attack our own ice cream shop.

Open: http://localhost:3000

---

# Demo 1 – SQL Injection

**Page:** Search Flavors

**Try this payload:**

```
' OR '1'='1
```

What happens?

---

# Why did that work?

Vulnerable native query:

```sql
SELECT * FROM flavors WHERE name LIKE %:keyword%
```

No proper parameterization in the vulnerable version.

---

# Demo 2 – Stored XSS

**Page:** Reviews

**Try this comment:**

```html
<script>alert('XSS from Ice Cream')</script>
```

Refresh the page → the script runs.

---

# Why is this dangerous?

- Stored in the database
- Rendered without sanitization
- Affects every user who views the reviews

---

# Demo 3 – IDOR (Broken Access Control)

**Page:** Orders + Admin API

- Change the order ID → view any order
- Call `/api/admin/users` or `/api/admin/stats` with no auth

No ownership or role checks.

---

# Demo 4 – Weak Authentication

**Page:** Login

Default credentials:

- `admin` / `softserve123`
- `student` / `password`

Problems:
- Plain text passwords
- No rate limiting
- Predictable accounts

---

# Demo 5 – Insecure File Upload (A08)

**Endpoint:** `POST /api/upload`

- No file type validation
- No extension whitelist
- Files stored with original name influence

---

---
layout: quiz
quizId: q1
question: What does IDOR stand for?
options:
  - { label: A, text: Insecure Direct Object Reference, correct: true }
  - { label: B, text: Internal Data Object Routing }
  - { label: C, text: Identity Domain Ownership Rule }
---

---
layout: quiz
quizId: q2
question: Which vulnerability allows an attacker to run JavaScript in another user’s browser?
options:
  - { label: A, text: SQL Injection }
  - { label: B, text: Stored XSS, correct: true }
  - { label: C, text: IDOR }
  - { label: D, text: CSRF }
---

---
layout: quiz
quizId: q3
question: Why are plain text passwords dangerous?
options:
  - { label: A, text: They take more storage space }
  - { label: B, text: If the database is leaked, attackers can use the passwords directly, correct: true }
  - { label: C, text: They make login slower }
---

---

# How Do We Fix These?

### SQL Injection
- Always use parameterized queries / prepared statements
- Prefer JPA method names or Criteria API

### Stored XSS
- Encode output
- Use a proper sanitizer
- Content Security Policy (CSP)

### IDOR / Admin Access
- Always verify ownership and roles
- Never trust user-supplied IDs alone

### Authentication
- Hash passwords (BCrypt / Argon2)
- Add rate limiting

### File Upload
- Whitelist extensions + validate content type
- Store outside web root

---

# Key Takeaways

1. Security is not optional
2. Never trust user input
3. Simple mistakes create serious vulnerabilities
4. Understanding attacks helps you write better code

---

# Resources

- OWASP Top 10: https://owasp.org/Top10/
- Project Repo: https://github.com/rajahpixelphone/scoops-of-brainfreeze
- Documentation inside the repo (`docs/`)

---

# Thank You!

**Questions?**

Feel free to explore the application and try breaking it further.

---

# Bonus Challenge

Can you find more vulnerabilities in Scoops of Brainfreeze?

Try:
- `/api/admin/users`
- `/api/admin/stats`
- File upload endpoint
