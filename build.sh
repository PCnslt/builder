#!/bin/bash
# GitHub History Builder - Build & Run Script
# This script builds and runs the GitHub History Builder

set -e

echo "╔════════════════════════════════════════════════════════════════╗"
echo "║   GitHub History Builder - Build & Run Script                 ║"
echo "║   Generate Realistic GitHub Contribution History              ║"
echo "╚════════════════════════════════════════════════════════════════╝"
echo ""

# Colors for output
GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
NC='\033[0m' # No Color

echo -e "${BLUE}[1/3]${NC} Building project..."
mvn clean package -DskipTests
echo -e "${GREEN}✓ Build complete!${NC}"
echo ""

echo -e "${BLUE}[2/3]${NC} Running GitHub History Builder..."
java -jar target/github-history-builder-1.0.0.jar "$@"
BUILD_EXIT=$?
echo ""

if [ $BUILD_EXIT -eq 0 ]; then
    echo -e "${GREEN}✓ Build successful!${NC}"
    echo ""

    if [ -d "generated-github-repo" ]; then
        echo -e "${YELLOW}📊 Generated Repository Statistics:${NC}"
        cd generated-github-repo
        COMMIT_COUNT=$(git rev-list --count HEAD)
        echo "   Total commits: $COMMIT_COUNT"
        echo "   Repository path: $(pwd)"
        cd ..
        echo ""

        echo -e "${YELLOW}📋 Next Steps:${NC}"
        echo "   1. cd generated-github-repo"
        echo "   2. Create a new empty repository on GitHub"
        echo "   3. git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPO.git"
        echo "   4. git push -u origin master"
        echo "   5. Visit https://github.com/YOUR_USERNAME to view your heatmap!"
        echo ""
    fi

    echo -e "${GREEN}✓ Ready to push to GitHub!${NC}"
else
    echo -e "${RED}✗ Build failed!${NC}"
    exit 1
fi

echo -e "${BLUE}[3/3]${NC} Completed!"
echo ""
echo -e "${GREEN}✨ Success! Your GitHub contribution history is ready!${NC}"

