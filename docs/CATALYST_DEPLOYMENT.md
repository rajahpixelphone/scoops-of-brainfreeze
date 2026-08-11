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

## 2. Build the React frontend into Spring Boot

```bash
# From project root
cd frontend
npm install
npm run build

# Copy the production build into Spring Boot static resources
rm -rf ../backend/src/main/resources/static
mkdir -p ../backend/src/main/resources/static
cp -r dist/* ../backend/src/main/resources/static/
```

After this step, Spring Boot will serve both the API and the React UI from the same origin.

---

## 3. Build the Spring Boot JAR

```bash
cd backend
./mvnw clean package -DskipTests
```

The JAR will be created at:
`backend/target/brainfreeze-0.0.1-SNAPSHOT.jar`

---

## 4. Initialize AppSail (first time only)

From the `backend` directory (or project root):

```bash
catalyst init
```

When prompted:

- **Select AppSail**
- **Stack**: Java 17 (or Java 21)
- **Platform**: Java SE
- **Build path**: point to the directory containing the JAR (usually `target/`)

---

## 5. Configure startup command

AppSail needs the app to listen on `X_ZOHO_CATALYST_LISTEN_PORT`.

The project already contains `ServerPortCustomizer.java` for this.

Example startup command (adjust JAR name if needed):

```bash
java -jar brainfreeze-0.0.1-SNAPSHOT.jar
```

You can also set it explicitly:

```bash
java -jar brainfreeze-0.0.1-SNAPSHOT.jar --server.port=$X_ZOHO_CATALYST_LISTEN_PORT
```

---

## 6. Deploy

```bash
catalyst deploy
```

or specifically:

```bash
catalyst deploy appsail
```

---

## 7. Access the application

After deployment, Catalyst will give you a public HTTPS URL.

Open it in the browser — both the React UI and the API will be available from the same origin.

---

## Important Security Notes

This application is **intentionally vulnerable**.

- Do **not** leave it permanently public.
- Prefer temporary demos or restrict access.
- H2 database is in-memory → data resets on every restart.
- File uploads are stored on ephemeral disk and will be lost on redeploy.

---

## Local verification before deploying

```bash
# After copying the React build into static/
cd backend
./mvnw spring-boot:run
```

Then open http://localhost:8080 — you should see the full UI served by Spring Boot.
