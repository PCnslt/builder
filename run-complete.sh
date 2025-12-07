#!/bin/bash
# GitHub History Builder - Complete Setup & Run Script

set -e

PROJECT_DIR="/Users/pcnslt/Library/Mobile Documents/com~apple~CloudDocs/mac-repo/mac-repo/Projects/github-history-builder"

echo "╔════════════════════════════════════════════════════════════════╗"
echo "║       GitHub History Builder - Build & Generate History       ║"
echo "╚════════════════════════════════════════════════════════════════╝"
echo ""

# Step 1: Navigate to project
echo "[1/5] Navigating to project directory..."
cd "$PROJECT_DIR"
echo "✓ Project directory: $(pwd)"
echo ""

# Step 2: Clean and build
echo "[2/5] Building project with Maven..."
echo "Command: mvn clean package -DskipTests"
mvn clean package -DskipTests
BUILD_STATUS=$?

if [ $BUILD_STATUS -ne 0 ]; then
    echo "✗ Build failed with status: $BUILD_STATUS"
    exit 1
fi
echo "✓ Build successful!"
echo ""

# Step 3: Verify JAR exists
echo "[3/5] Verifying JAR file..."
if [ -f "target/github-history-builder-1.0.0.jar" ]; then
    JAR_SIZE=$(du -h target/github-history-builder-1.0.0.jar | cut -f1)
    echo "✓ JAR created: target/github-history-builder-1.0.0.jar ($JAR_SIZE)"
else
    echo "✗ JAR file not found!"
    exit 1
fi
echo ""

# Step 4: Run the application
echo "[4/5] Running GitHub History Builder..."
echo "Generating 5 years of contribution history..."
echo ""
java -jar target/github-history-builder-1.0.0.jar
RUN_STATUS=$?

if [ $RUN_STATUS -ne 0 ]; then
    echo "✗ Application failed with status: $RUN_STATUS"
    exit 1
fi
echo ""

# Step 5: Display results
echo "[5/5] Results Summary"
echo "════════════════════════════════════════════════════════════════"
echo ""

if [ -d "generated-github-repo" ]; then
    cd generated-github-repo
    COMMIT_COUNT=$(git rev-list --count HEAD 2>/dev/null || echo "unknown")
    REPO_SIZE=$(du -sh . | cut -f1)

    echo "✓ Repository created successfully!"
    echo "  Location: $(pwd)"
    echo "  Size: $REPO_SIZE"
    echo "  Commits: $COMMIT_COUNT"
    echo "  Git status:"
    git log --oneline | head -5
    echo "  ..."
    echo ""

    cd ..

    echo "╔════════════════════════════════════════════════════════════════╗"
    echo "║                   NEXT STEPS - PUSH TO GITHUB                   ║"
    echo "╠════════════════════════════════════════════════════════════════╣"
    echo "║                                                                ║"
    echo "║  1. Create a NEW EMPTY repository on GitHub:                  ║"
    echo "║     https://github.com/new                                    ║"
    echo "║     (Do NOT initialize with README, .gitignore, or license)   ║"
    echo "║                                                                ║"
    echo "║  2. Copy your repository URL, then run:                       ║"
    echo "║                                                                ║"
    echo "║     cd generated-github-repo                                  ║"
    echo "║     git remote add origin <YOUR-REPO-URL>                     ║"
    echo "║     git push -u origin master                                 ║"
    echo "║                                                                ║"
    echo "║  3. Visit your GitHub profile:                                ║"
    echo "║     https://github.com/YOUR_USERNAME                          ║"
    echo "║                                                                ║"
    echo "║  4. Your contribution heatmap is now populated! 🎉            ║"
    echo "║                                                                ║"
    echo "╚════════════════════════════════════════════════════════════════╝"
else
    echo "✗ Repository was not created!"
    exit 1
fi

echo ""
echo "✨ SUCCESS! Your GitHub history has been generated! ✨"
echo ""
echo "Generated repository: $PROJECT_DIR/generated-github-repo"
echo ""

