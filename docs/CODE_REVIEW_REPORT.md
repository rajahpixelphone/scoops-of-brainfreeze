# Code Review Report
**Project:** Scoops of Brainfreeze  
**Role:** Reviewer  
**Date:** 10 August 2026  
**Scope:** Backend, Frontend, Slides, Documentation

---

## 1. Overall Assessment

**Verdict: Good – Lecture Ready with Minor Improvements Recommended**

The project successfully achieves its primary goal: providing a fun, relatable, and deliberately vulnerable application for teaching OWASP Top 10:2025 to 3rd-year CSE students. The ice cream theme is excellent for engagement. Core vulnerabilities are implemented clearly and are easy to demonstrate live.

**Strengths:**
- Clear intentional vulnerabilities with good comments
- Relatable theme that aids teaching
- Working full-stack demo (Backend + React + Slides)
- Decent documentation with Mermaid diagrams
- TDD-style tests that document vulnerable behavior

**Areas for Improvement:**
- Some vulnerabilities are incomplete or only partially implemented
- Frontend is functional but basic
- Quiz integration with `slidev-addon-slide-quiz` is not fully utilized
- Missing a few promised features (file upload, admin panel)
- SecurityConfig is present but could be better documented in slides

---

## 2. Backend Review

### 2.1 Strengths

| Item | Observation |
|------|-------------|
| Intentional vulnerabilities | Clearly marked with comments (IDOR, SQLi, XSS, weak auth, etc.) |
| FlavorRepository | Native query is correctly written as vulnerable |
| OrderController | Good demonstration of IDOR + negative quantity (A06) |
| AuthController | Plain-text password comparison + no rate limiting – clear for teaching |
| ReviewController | No sanitization – perfect for Stored XSS |
| DataLoader | Useful sample data + weak default credentials |
| SecurityConfig | Intentionally open (CSRF disabled, CORS `*`, permitAll) |
| Tests | Present for key vulnerable paths and document expected insecure behavior |

### 2.2 Issues & Recommendations

| Severity | Issue | Recommendation |
|----------|-------|----------------|
| Medium | SQL Injection query uses `LIKE %:keyword%` which may not always behave as classic SQLi depending on the driver | Consider a more classic vulnerable pattern or document the exact behavior |
| Medium | No actual Admin panel endpoint exists despite being mentioned in docs | Either implement a simple `/api/admin/**` or remove from documentation |
| Low | Passwords stored in plain text (good for demo) but comments sometimes mention MD5 | Keep consistent – currently plain text is fine |
| Low | `Order.createOrder` hardcodes price calculation (`* 120.0`) | Acceptable for demo, but could use the Flavor price |
| Low | No global exception handler | Would help demonstrate A10 more clearly |
| Info | File upload (A08) is documented but not implemented | Add if time permits |

### 2.3 Vulnerability Coverage (Backend)

| OWASP | Implemented? | Quality |
|-------|--------------|---------|
| A01 Broken Access Control | Yes (IDOR) | Good |
| A02 Security Misconfiguration | Yes | Good |
| A03 Supply Chain | No | Mentioned only |
| A04 Cryptographic Failures | Yes (plain text) | Good |
| A05 Injection | Yes (SQLi + XSS) | Good |
| A06 Insecure Design | Yes (negative qty) | Good |
| A07 Authentication Failures | Yes | Good |
| A08 Software/Data Integrity | No | Missing |
| A09 Logging Failures | No | Missing |
| A10 Exception Handling | Partial | Weak |

---

## 3. Frontend Review

### 3.1 Strengths

- Clean and simple React + Vite setup
- Pages map well to vulnerabilities (Search → SQLi, Reviews → XSS, Orders → IDOR, Login → Weak Auth)
- `dangerouslySetInnerHTML` is correctly used in Reviews for XSS demo
- Basic but pleasant ice-cream themed CSS
- Routing is clear

### 3.2 Issues & Recommendations

| Severity | Issue | Recommendation |
|----------|-------|----------------|
| Medium | Hardcoded `http://localhost:8080` in every page | Use a shared `api.js` or environment variable |
| Medium | No error handling / loading states | Add basic loading and error UI |
| Low | No authentication state management | After login, user info is only in localStorage – not used elsewhere |
| Low | Reviews always post to `flavorId: 1` | Acceptable for demo |
| Info | No protected routes | Fine for educational purpose |

### 3.3 Positive Note
The frontend is intentionally simple, which is correct for a teaching tool. Over-engineering would reduce clarity.

---

## 4. Slides Review (Slidev)

### 4.1 Strengths

- Good narrative flow for a 60-minute session
- Clear demo instructions with payloads
- Architecture diagram included
- Quiz placeholders present
- Practical “How to Fix” section
- Bonus challenge at the end – nice touch

### 4.2 Issues & Recommendations

| Severity | Issue | Recommendation |
|----------|-------|----------------|
| High | `slidev-addon-slide-quiz` is declared but not properly used with the addon’s expected syntax | Research the exact component syntax of the addon and implement real interactive quizzes |
| Medium | Quizzes are currently static markdown checkboxes | Convert to live QR + real-time results as originally planned |
| Medium | No timing guidance (which demo takes how long) | Add rough time boxes for the speaker |
| Low | Some slides are text-heavy | Consider more visual / code-focused slides |
| Info | Missing slide for SecurityConfig / A02 deep dive | Optional enhancement |

### 4.3 Suggested Slide Improvements

1. Add a “Demo Checklist” slide for the speaker
2. Make quizzes truly interactive with the addon
3. Add one slide showing the vulnerable vs fixed code side-by-side
4. Include a slide with the default credentials prominently

---

## 5. Documentation Review

**docs/ARCHITECTURE_AND_VULNERABILITIES.md** is solid.

**Strengths:**
- Good structure
- Mermaid diagrams for architecture and two attack flows
- Clear mapping of each OWASP category
- Installation instructions present

**Gaps:**
- Mentions features that are not yet implemented (Admin panel, File upload, Contact form)
- A03, A08, A09 are mostly theoretical in the current codebase

**Recommendation:** Update the documentation to reflect only what is currently implemented, or mark unimplemented items as “Planned”.

---

## 6. Testing Review

**Present Tests:**
- `FlavorRepositoryTest` – documents vulnerable search behavior
- `OrderControllerTest` – documents IDOR
- `ReviewControllerTest` – documents Stored XSS

**Assessment:**
The tests correctly follow the educational goal (they assert the *vulnerable* behavior). This is appropriate for a deliberately vulnerable application.

**Missing:**
- Tests for AuthController
- Tests for negative quantity business logic
- Integration tests that simulate full attack flows

---

## 7. Priority Recommendations

### Must Fix / Improve before Lecture
1. Make the Slidev quizzes actually interactive using the addon correctly
2. Extract API base URL in frontend to a single place
3. Align documentation with implemented features

### Should Improve
4. Add a simple global exception handler (A10 demo)
5. Add basic loading/error states in frontend
6. Add speaker notes or timing to slides

### Nice to Have
7. Implement a minimal file upload endpoint (A08)
8. Add a very simple Admin page
9. Side-by-side “Vulnerable vs Fixed” code examples in slides

---

## 8. Final Scorecard

| Area              | Score (out of 10) | Comments |
|-------------------|-------------------|----------|
| Educational Value | 9.0               | Excellent theme and clear vulnerabilities |
| Backend Quality   | 7.5               | Solid intentional flaws, some gaps |
| Frontend Quality  | 7.0               | Functional and clear, but basic |
| Slides Quality    | 7.5               | Good structure, quiz addon underused |
| Documentation     | 8.0               | Strong, needs sync with code |
| Test Coverage     | 6.5               | Good direction, limited scope |
| **Overall**       | **7.8**           | Ready for lecture with minor polish |

---

## 9. Conclusion

**Scoops of Brainfreeze** is a well-conceived educational project. The core idea (ice cream shop + intentional OWASP vulnerabilities) is strong and will engage students effectively. The backend vulnerabilities are clearly implemented and easy to demonstrate. The main gaps are in the interactive quiz implementation and incomplete coverage of all 10 OWASP categories.

With the priority recommendations above addressed, this will be an excellent 1-hour guest lecture.

---

**Reviewed by:** Grok (Reviewer role)  
**Repository:** https://github.com/rajahpixelphone/scoops-of-brainfreeze
