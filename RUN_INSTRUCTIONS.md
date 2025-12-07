# 🚀 GitHub History Builder - Build & Run Instructions

## Quick Start (Recommended)

### Option 1: Use the Provided Script (Easiest)

```bash
# Make the script executable
chmod +x "/Users/pcnslt/Library/Mobile Documents/com~apple~CloudDocs/mac-repo/mac-repo/Projects/github-history-builder/run-complete.sh"

# Run it
bash "/Users/pcnslt/Library/Mobile Documents/com~apple~CloudDocs/mac-repo/mac-repo/Projects/github-history-builder/run-complete.sh"
```

This script will:
1. ✅ Navigate to project directory
2. ✅ Build with Maven
3. ✅ Verify JAR creation
4. ✅ Run the application
5. ✅ Display results and next steps

### Option 2: Manual Build & Run

```bash
# Step 1: Navigate to project
cd "/Users/pcnslt/Library/Mobile Documents/com~apple~CloudDocs/mac-repo/mac-repo/Projects/github-history-builder"

# Step 2: Build
mvn clean package -DskipTests

# Step 3: Run
java -jar target/github-history-builder-1.0.0.jar
```

---

## What the Build Does

### Build Phase
- ✅ Downloads dependencies (first time only)
- ✅ Compiles Java source code
- ✅ Runs tests (if included)
- ✅ Creates executable JAR file

**Output:** `target/github-history-builder-1.0.0.jar`

### Run Phase
- ✅ Generates 5 years of contribution history
- ✅ Creates commits with realistic patterns
- ✅ Writes to `generated-github-repo/` directory
- ✅ Initializes Git repository

**Output:** `generated-github-repo/` with ~10,000-20,000 commits

---

## Expected Output

When you run the application, you'll see:

```
[INFO] Scanning for projects...
[INFO] Building GitHub History Builder 1.0.0
...
[INFO] BUILD SUCCESS
...

Starting GitHub History Builder
Configuration loaded successfully...
Generating commits using INTELLIGENT pattern
Progress: 365/1825 commits written (20%)
...
Generated 18,250 commits in 2 minutes 35 seconds
✓ GitHub history successfully generated!

Repository created at: /path/to/generated-github-repo
```

---

## Next Steps After Generation

### 1. Create an Empty GitHub Repository

Go to https://github.com/new and:
- ✅ Enter repository name
- ✅ Choose "Public" or "Private"
- ✅ **DO NOT** initialize with README, .gitignore, or license
- ✅ Click "Create repository"

Copy the repository URL (e.g., `https://github.com/YOUR_USERNAME/YOUR_REPO.git`)

### 2. Push the Generated History

```bash
cd generated-github-repo

# Add the remote
git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPO.git

# Push to GitHub
git push -u origin master
```

### 3. View Your Contribution Heatmap

Visit your GitHub profile:
```
https://github.com/YOUR_USERNAME
```

**Your contribution heatmap should now show activity for the past 5 years!** 🎉

---

## Customization Options

### Generate Different Contribution Patterns

```bash
# Very active contributor (10 years, 5-15 commits/day)
java -jar target/github-history-builder-1.0.0.jar \
  --years 10 \
  --min-commits 5 \
  --max-commits 15 \
  --probability 0.7

# Casual contributor (3 years, 1-3 commits/day)
java -jar target/github-history-builder-1.0.0.jar \
  --years 3 \
  --min-commits 1 \
  --max-commits 3 \
  --probability 0.2

# Use custom configuration
java -jar target/github-history-builder-1.0.0.jar \
  --config src/main/resources/config/aggressive-config.yaml
```

### All Available Options

```
-y, --years              Years of history (default: 5)
-n, --name               Author name (default: Contribution Bot)
-e, --email              Author email (default: bot@example.com)
-m, --min-commits        Min commits per day (default: 1)
-M, --max-commits        Max commits per day (default: 5)
-p, --probability        Contribution probability (default: 0.4)
-r, --repository         Output path (default: ./generated-github-repo)
-c, --config             Configuration file path
--dry-run                Preview without creating repo
-v, --verbose            Verbose logging
-h, --help               Show help
```

---

## Troubleshooting

### Build Issues

**Problem:** `mvn: command not found`
```bash
# Solution: Install Maven
brew install maven
```

**Problem:** `java: command not found`
```bash
# Solution: Install Java 11+
brew install java
java -version  # Should show Java 11+
```

**Problem:** Build fails with dependency errors
```bash
# Solution: Try with force update
mvn clean package -U -DskipTests
```

### Runtime Issues

**Problem:** Not enough commits generated
- Increase `--probability` (try 0.7 instead of 0.4)
- Increase `--max-commits` (try 10 instead of 5)

**Problem:** Repository too large
- Reduce `--years` (try 3 instead of 5)
- Reduce `--max-commits` (try 3 instead of 5)

**Problem:** Git push fails
- Verify repository URL is correct
- Ensure repository is empty on GitHub
- Check Git is configured: `git config --global user.name`

---

## File Locations

| Item | Location |
|------|----------|
| Project | `/Users/pcnslt/Library/Mobile Documents/com~apple~CloudDocs/mac-repo/mac-repo/Projects/github-history-builder` |
| JAR | `target/github-history-builder-1.0.0.jar` |
| Generated Repo | `generated-github-repo/` |
| Logs | `github-history-builder.log` |
| Config Examples | `src/main/resources/config/` |

---

## Time Estimates

| Task | Time |
|------|------|
| First build (with downloads) | 3-5 minutes |
| Subsequent builds | 30-60 seconds |
| Generating history | 2-3 minutes |
| Pushing to GitHub | 1-2 minutes |
| **Total** | **6-11 minutes** |

---

## Success Indicators

After running successfully, you should see:

✅ `target/github-history-builder-1.0.0.jar` exists  
✅ `generated-github-repo/` directory created  
✅ `cd generated-github-repo && git log` shows commits  
✅ Commits span 5 years  
✅ `github-history-builder.log` contains execution log  

---

## Example Session

```bash
# 1. Build
cd github-history-builder
mvn clean package -DskipTests
# ✓ BUILD SUCCESS

# 2. Run
java -jar target/github-history-builder-1.0.0.jar
# ✓ Generated 18,250 commits

# 3. Verify
cd generated-github-repo
git log --oneline | head -10
# Shows commits from 5 years ago

# 4. Check statistics
git rev-list --count HEAD
# Shows total commit count (~18,250)

# 5. Push to GitHub
git remote add origin https://github.com/yourusername/your-repo.git
git push -u origin master
# ✓ Pushed successfully

# 6. View on GitHub
# Visit https://github.com/yourusername/your-repo
# ✓ Heatmap is populated!
```

---

## Documentation References

- **README.md** - Complete user guide
- **QUICKSTART.md** - 5-minute quick start
- **ARCHITECTURE.md** - Technical documentation
- **INDEX.md** - Navigation and references

---

## Support

If you encounter issues:

1. Check the log file: `github-history-builder.log`
2. Run with verbose flag: `java -jar ... --verbose`
3. Try dry-run mode: `java -jar ... --dry-run`
4. Review troubleshooting section above

---

**Ready to generate your GitHub history? Start with the Quick Start section above!** 🚀

