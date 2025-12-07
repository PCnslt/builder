# Quick Start Guide - GitHub History Builder

## 5 Minute Setup

### 1. Build the Project

```bash
cd github-history-builder
mvn clean package -DskipTests
```

Output: `target/github-history-builder-1.0.0.jar`

### 2. Generate History (No Options)

```bash
java -jar target/github-history-builder-1.0.0.jar
```

This creates a repo with:
- 5 years of history
- Intelligent distribution (weekday bias + seasons)
- 1-5 commits per active day
- 40% contribution probability

### 3. Check Generated Repository

```bash
ls -la generated-github-repo/
cd generated-github-repo
git log --oneline | head -20
```

### 4. Push to GitHub

```bash
# Create empty repository on GitHub (NO README)

# In the generated-github-repo folder:
git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPO.git
git push -u origin master
```

### 5. View Your Profile

Go to https://github.com/YOUR_USERNAME and see your contribution heatmap! 🎉

---

## Common Use Cases

### Very Active Developer (10 years)

```bash
java -jar target/github-history-builder-1.0.0.jar \
  --years 10 \
  --min-commits 5 \
  --max-commits 15 \
  --probability 0.7 \
  --name "Your Name" \
  --email "your@email.com"
```

### Casual Contributor (3 years)

```bash
java -jar target/github-history-builder-1.0.0.jar \
  --years 3 \
  --min-commits 1 \
  --max-commits 3 \
  --probability 0.2
```

### Test Run (No Repository Created)

```bash
java -jar target/github-history-builder-1.0.0.jar --dry-run
```

### From Configuration File

```bash
java -jar target/github-history-builder-1.0.0.jar \
  --config src/main/resources/config/aggressive-config.yaml
```

---

## Configuration Options

```
-y, --years              Years of history (default: 5)
-n, --name               Author name (default: Contribution Bot)
-e, --email              Author email (default: bot@example.com)
-m, --min-commits        Min commits per day (default: 1)
-M, --max-commits        Max commits per day (default: 5)
-p, --probability        Contribution probability 0-1 (default: 0.4)
-r, --repository         Output path (default: ./generated-github-repo)
-c, --config             Config file (YAML/JSON)
--dry-run                Preview without creating repo
-v, --verbose            Verbose logging
-h, --help               Show help
```

---

## Troubleshooting

### "mvn: command not found"
Install Maven: `brew install maven`

### "java: command not found"
Install Java 11+: `brew install java`

### Build fails with dependency errors
Try: `mvn clean package -U` (force update)

### Repository too large
Reduce commits:
- Lower `--max-commits`
- Lower `--probability`
- Reduce `--years`

---

## Next Steps

1. **Customize**: Create your own config file
2. **Extend**: Modify generators for different patterns
3. **Integrate**: Use programmatically in your code:

```java
HistoryConfig config = ConfigurationLoader.createDefault();
config.setAuthorName("Your Name");
config.setYearsBack(5);

GitHistoryBuilder builder = new GitHistoryBuilder(config);
ExecutionReport report = builder.build();
report.printSummary();
```

---

**Happy Contributing! 🚀**

