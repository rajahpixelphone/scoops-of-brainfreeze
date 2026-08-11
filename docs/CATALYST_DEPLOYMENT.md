# Deploying Scoops of Brainfreeze on Zoho Catalyst (Option A)

This guide embeds the React frontend inside the Spring Boot JAR and deploys everything as a single **AppSail** service.

---

## 1. Prerequisites

- Java 17+
- Node.js 18+
- Maven
- Catalyst CLI (`npm install -g zcatalyst-cli`)
- A Catalyst project created in the console

---

## 2. One-command build (Recommended)

From the project root:

```bash
chmod +x build-for-catalyst.sh
./build-for-catalyst.sh
```

This script will:
1. Build the React frontend
2. Copy the production files into `backend/src/main/resources/static/`
3. Build the Spring Boot JAR

At the end it prints the JAR path.

---

## 3. Manual build (alternative)

```bash
# Frontend
cd frontend
npm install
npm run build

rm -rf ../backend/src/main/resources/static
mkdir -p ../backend/src/main/resources/static
cp -r dist/* ../backend/src/main/resources/static/

# Backend
cd ../backend
./mvnw clean package -DskipTests
```

---

## 4. Initialize AppSail (first time only)

From the `backend` directory:

```bash
catalyst init
```

When prompted:

- **Select AppSail**
- **Stack**: Java 17 (or Java 21)
- **Platform**: Java SE
- **Build path**: `target/` (directory containing the JAR)

---

## 5. Startup command

The project already includes `ServerPortCustomizer.java` which reads `X_ZOHO_CATALYST_LISTEN_PORT`.

Typical startup command:

```bash
java -jar brainfreeze-0.0.1-SNAPSHOT.jar
```

---

## 6. Deploy

```bash
cd backend
catalyst deploy
```

or:

```bash
catalyst deploy appsail
```

---

## 7. Access the application

Catalyst will provide a public HTTPS URL.  
Both the React UI and the API are served from the same origin.

---

## Local verification before deploying

```bash
./build-for-catalyst.sh
cd backend
java -jar target/brainfreeze-0.0.1-SNAPSHOT.jar
```

Then open http://localhost:8080

---

## Important Security Notes

This application is **intentionally vulnerable**.

- Do **not** leave it permanently public.
- Prefer temporary demos only.
- H2 database is in-memory → data resets on every restart.
- File uploads are stored on ephemeral disk and will be lost on redeploy.
