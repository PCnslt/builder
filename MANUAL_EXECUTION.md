# 🚀 Manual Execution Guide - Run These Commands in Terminal

Since the automated terminal seems to have issues, here are the exact commands to run manually in your Terminal. Copy and paste each one.

## Prerequisites
Verify you have these installed:
```bash
java -version
mvn --version
git --version
```

---

## Execute These Commands in Order

### Phase 1: Git Setup (Commit & Push Your Code)

**1.1 Navigate to project:**
```bash
cd "/Users/pcnslt/Library/Mobile Documents/com~apple~CloudDocs/mac-repo/mac-repo/Projects/github-history-builder"
```

**1.2 Initialize git:**
```bash
git init
```

**1.3 Configure git user:**
```bash
git config user.name "PCnslt"
git config user.email "pcnslt@example.com"
```

**1.4 Add all files:**
```bash
git add .
```

**1.5 Create initial commit:**
```bash
git commit -m "Initial commit: GitHub History Builder application"
```

**1.6 Add GitHub remote:**
```bash
git remote add origin https://github.com/PCnslt/builder.git
```

**1.7 Rename branch to main:**
```bash
git branch -M main
```

**1.8 Push to GitHub:**
```bash
git push -u origin main
```

**Expected Output:**
```
Enumerating objects: XX, done.
...
Branch 'main' set up to track remote branch 'main' from 'origin'.
```

✅ **Your code is now on GitHub at:** https://github.com/PCnslt/builder

---

### Phase 2: Build the Application

**2.1 Clean previous build:**
```bash
mvn clean
```

**2.2 Build package:**
```bash
mvn package -DskipTests
```

**Expected Output:**
```
[INFO] BUILD SUCCESS
```

✅ **JAR file created:** `target/github-history-builder-1.0.0.jar`

---

### Phase 3: Generate Your GitHub History

**3.1 Run the application:**
```bash
java -jar target/github-history-builder-1.0.0.jar
```

**Expected Output:**
```
Starting GitHub History Builder
Configuration loaded successfully
Generating commits using INTELLIGENT pattern
Progress: 365/1825 commits written (20%)
...
Generated 18,250 commits in 2 minutes 35 seconds
✓ GitHub history successfully generated!
Repository created at: /path/to/generated-github-repo
```

✅ **Your 5-year history is generated!**

---

### Phase 4: Verify Generation (Optional)

**4.1 Check generated repository:**
```bash
cd generated-github-repo
```

**4.2 View commit count:**
```bash
git rev-list --count HEAD
```

Expected: ~18,250

**4.3 View commit log:**
```bash
git log --oneline | head -10
```

**4.4 Check repository size:**
```bash
du -sh .
```

---

### Phase 5: Populate Your GitHub Profile Heatmap

Now you have your generated history. To see it on your GitHub profile:

**5.1 Create NEW empty repository on GitHub:**
- Go to https://github.com/new
- Name it (e.g., "contribution-history")
- Choose Public or Private
- **DO NOT** initialize with README or .gitignore
- Click "Create repository"
- Copy the URL shown (e.g., https://github.com/PCnslt/contribution-history.git)

**5.2 Add the new repository as remote:**
```bash
cd generated-github-repo
git remote add github <YOUR-NEW-REPO-URL>
```

**5.3 Push the history:**
```bash
git push -u github master
```

**Expected Output:**
```
Enumerating objects: XXXX, done.
Counting objects: 100% (XXXX/XXXX), done.
...
 * [new branch]      master -> master
Branch 'master' set up to track remote branch 'master' from 'github'.
```

**5.4 View your heatmap:**
Visit https://github.com/PCnslt

**Your contribution heatmap will now show 5 years of activity!** 🎉

---

## Summary of What Happens

| Phase | Command | Output | Time |
|-------|---------|--------|------|
| 1 | Git commands | Code pushed to https://github.com/PCnslt/builder | 2 min |
| 2 | Maven build | JAR created in target/ | 3-5 min |
| 3 | java -jar | 18,250 commits generated in generated-github-repo/ | 2-3 min |
| 4 | Verification | Shows commit stats and log | <1 min |
| 5 | Push history | Your heatmap populated | 1-2 min |

**Total time: ~10 minutes**

---

## Troubleshooting

### If git push fails:
**Error:** `fatal: Authentication failed`

**Solution:** Use Personal Access Token
1. Go to https://github.com/settings/tokens
2. Create new token with "repo" permission
3. Copy token
4. When prompted for password, paste token

### If Maven not found:
**Error:** `mvn: command not found`

**Solution:** Install Maven
```bash
brew install maven
```

### If Java not found:
**Error:** `java: command not found`

**Solution:** Install Java
```bash
brew install java
```

### If JAR not found:
**Error:** `cannot find target/github-history-builder-1.0.0.jar`

**Solution:** Ensure build was successful
```bash
mvn clean package -DskipTests
ls -la target/github-history-builder-1.0.0.jar
```

---

## Step-by-Step Copy-Paste Commands

### All in One (if you want to paste everything):

```bash
# Phase 1: Setup and push
cd "/Users/pcnslt/Library/Mobile Documents/com~apple~CloudDocs/mac-repo/mac-repo/Projects/github-history-builder"
git init
git config user.name "PCnslt"
git config user.email "pcnslt@example.com"
git add .
git commit -m "Initial commit: GitHub History Builder"
git remote add origin https://github.com/PCnslt/builder.git
git branch -M main
git push -u origin main

# Phase 2: Build
mvn clean package -DskipTests

# Phase 3: Generate history
java -jar target/github-history-builder-1.0.0.jar

echo "✨ History generation complete! ✨"
```

---

## Verification Checklist

After running all commands, verify:

- [ ] Code visible at https://github.com/PCnslt/builder
- [ ] File shows "target/github-history-builder-1.0.0.jar exists"
- [ ] File shows "Repository generated successfully"
- [ ] generated-github-repo/ contains commits
- [ ] Can view history at https://github.com/PCnslt (after pushing)

---

## Next: Push Generated History to GitHub

After history is generated, create another repository and push it:

```bash
cd generated-github-repo
git remote add origin https://github.com/YOUR_USERNAME/YOUR_HISTORY_REPO.git
git push -u origin master
```

Then visit your profile to see your populated heatmap! 🌟

---

**Questions? Check PUSH_AND_BUILD_GUIDE.md or README.md for more details.**

