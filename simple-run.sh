#!/bin/bash
# Simplified setup script with minimal dependencies

set -e

PROJ="/Users/pcnslt/Library/Mobile Documents/com~apple~CloudDocs/mac-repo/mac-repo/Projects/github-history-builder"

echo "GitHub History Builder - Starting Setup..."
cd "$PROJ"

echo "Step 1: Git setup..."
git init 2>/dev/null || true
git config user.name "PCnslt"
git config user.email "pcnslt@example.com"
git add .
git commit -m "GitHub History Builder" 2>/dev/null || true

echo "Step 2: Add GitHub remote..."
git remote add origin https://github.com/PCnslt/builder.git 2>/dev/null || git remote set-url origin https://github.com/PCnslt/builder.git
git branch -M main 2>/dev/null || true

echo "Step 3: Push to GitHub..."
git push -u origin main

echo "Step 4: Build..."
mvn clean package -DskipTests

echo "Step 5: Generate history..."
java -jar target/github-history-builder-1.0.0.jar

echo "Complete!"

