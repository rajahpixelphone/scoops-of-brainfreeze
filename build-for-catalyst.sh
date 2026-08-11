#!/usr/bin/env bash
set -euo pipefail

# ============================================================
# Scoops of Brainfreeze – Build for Zoho Catalyst (Option A)
# Embeds React frontend into Spring Boot and produces the JAR
# ============================================================

ROOT_DIR="$(cd "$(dirname "$0")" && pwd)"
FRONTEND_DIR="$ROOT_DIR/frontend"
BACKEND_DIR="$ROOT_DIR/backend"
STATIC_DIR="$BACKEND_DIR/src/main/resources/static"

echo "============================================"
echo " Scoops of Brainfreeze – Catalyst Build"
echo "============================================"
echo

# ---- 1. Build React frontend ----
echo "[1/3] Building React frontend..."
cd "$FRONTEND_DIR"

if [ ! -d "node_modules" ]; then
  echo "      Installing npm dependencies..."
  npm install
fi

npm run build
echo "      Frontend build complete."
echo

# ---- 2. Copy frontend into Spring Boot static resources ----
echo "[2/3] Embedding frontend into Spring Boot..."
rm -rf "$STATIC_DIR"
mkdir -p "$STATIC_DIR"
cp -r "$FRONTEND_DIR/dist/"* "$STATIC_DIR/"
echo "      Copied to backend/src/main/resources/static/"
echo

# ---- 3. Build Spring Boot JAR ----
echo "[3/3] Building Spring Boot JAR..."
cd "$BACKEND_DIR"
./mvnw clean package -DskipTests
echo

JAR_FILE=$(find target -name "*.jar" ! -name "*-sources.jar" ! -name "*-javadoc.jar" | head -n 1)

if [ -z "$JAR_FILE" ]; then
  echo "ERROR: JAR file not found in backend/target/"
  exit 1
fi

echo "============================================"
echo " BUILD SUCCESSFUL"
echo "============================================"
echo "JAR location: $BACKEND_DIR/$JAR_FILE"
echo
echo "Next steps:"
echo "  1. cd backend"
echo "  2. catalyst deploy"
echo
echo "Or test locally first:"
echo "  java -jar $JAR_FILE"
echo "  Then open http://localhost:8080"
echo "============================================"
