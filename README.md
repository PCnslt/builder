# GitHub History Builder

Generate realistic fake GitHub contribution history spanning 5+ years with customizable patterns and distribution algorithms.

## Overview

This Java project creates a Git repository with backdated commits that populate your GitHub contribution heatmap. It supports various distribution patterns to create realistic-looking contribution histories.

**⚠️ Disclaimer**: This tool is for educational and testing purposes only. Use responsibly and only on your own GitHub accounts.

## Features

- ✨ **5+ Years of History**: Generate contributions spanning any time period
- 📊 **Multiple Distribution Patterns**: 
  - RANDOM: Completely random distribution
  - INTELLIGENT: Weekday bias with seasonal variations
  - SEASONAL: Strong seasonal variations
  - WEEKDAY_BIASED: Heavy weekday focus, light weekends
- 🎯 **Customizable Parameters**:
  - Commits per day (min/max)
  - Contribution probability
  - Weekday/weekend bias
  - Date ranges
- 🔧 **Configuration Files**: YAML and JSON support
- 📈 **Progress Tracking**: Real-time progress updates
- 🏃 **Dry-Run Mode**: Preview without creating repository
- 📝 **Comprehensive Logging**: Detailed execution logs

## Building from Source

### Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- Git (for repository operations)

### Build

```bash
# Clone or download the project
cd github-history-builder

# Build with Maven
mvn clean package

# This creates an executable JAR at:
# target/github-history-builder-1.0.0.jar
```

## Usage

### Command Line Interface

```bash
java -jar github-history-builder-1.0.0.jar [options]
```

#### Basic Usage (Default)

```bash
java -jar github-history-builder-1.0.0.jar
```

This creates a repository with 5 years of history using intelligent distribution.

#### Common Options

```bash
# Specify years of history
java -jar github-history-builder-1.0.0.jar --years 10

# Set author information
java -jar github-history-builder-1.0.0.jar --name "John Doe" --email "john@example.com"

# Customize commit frequency
java -jar github-history-builder-1.0.0.jar --min-commits 2 --max-commits 8

# Adjust contribution probability
java -jar github-history-builder-1.0.0.jar --probability 0.6

# Specify output location
java -jar github-history-builder-1.0.0.jar --repository /path/to/repo

# Preview without creating repository
java -jar github-history-builder-1.0.0.jar --dry-run

# Verbose logging
java -jar github-history-builder-1.0.0.jar --verbose
```

### Configuration Files

Use YAML or JSON configuration files for complex setups:

```bash
java -jar github-history-builder-1.0.0.jar --config config.yaml
java -jar github-history-builder-1.0.0.jar --config config.json
```

#### Example YAML Configuration

```yaml
author_name: "John Developer"
author_email: "john@example.com"

years_back: 7
pattern: INTELLIGENT

commits_per_day_min: 1
commits_per_day_max: 5

contribution_probability: 0.4
weekday_probability_boost: 1.5
weekend_probability_reduction: 0.5

repository_path: "./my-history-repo"

dry_run: false
verbose: false
```

#### Configuration Options

| Option | Type | Default | Description |
|--------|------|---------|-------------|
| `author_name` | String | "Contribution Bot" | Author name for commits |
| `author_email` | String | "bot@example.com" | Author email for commits |
| `years_back` | Integer | 5 | How many years of history to generate |
| `start_date` | LocalDate | (calculated) | Explicit start date (overrides years_back) |
| `end_date` | LocalDate | Today | Explicit end date |
| `pattern` | Enum | INTELLIGENT | Distribution pattern |
| `commits_per_day_min` | Integer | 1 | Minimum commits per active day |
| `commits_per_day_max` | Integer | 5 | Maximum commits per active day |
| `contribution_probability` | Double | 0.4 | Probability (0.0-1.0) of commits on any day |
| `weekday_probability_boost` | Double | 1.5 | Multiplier for weekday probability |
| `weekend_probability_reduction` | Double | 0.5 | Multiplier for weekend probability |
| `repository_path` | String | "./generated-github-repo" | Where to create the repository |
| `dry_run` | Boolean | false | Preview without creating repo |
| `verbose` | Boolean | false | Enable verbose logging |

## Distribution Patterns

### RANDOM
Completely random distribution. Any day has equal probability of having commits.

```bash
java -jar github-history-builder-1.0.0.jar --pattern RANDOM
```

### INTELLIGENT
Default pattern. Applies weekday bias and seasonal variations:
- 20% more likely on weekdays
- 50% less likely on weekends
- Seasonal adjustments (lower in summer, higher in fall)

```bash
java -jar github-history-builder-1.0.0.jar --pattern INTELLIGENT
```

### SEASONAL
Strong seasonal variations:
- Winter: 80% of base probability
- Spring: 110% of base probability
- Summer: 70% of base probability
- Fall: 130% of base probability

### WEEKDAY_BIASED
Heavy weekday focus with light weekend activity.

## Next Steps After Generation

Once the repository is generated:

```bash
# Navigate to the repository
cd ./generated-github-repo

# Create a new repository on GitHub (empty, no README)
# Then:
git remote add origin https://github.com/yourusername/your-repo.git

# Push the history
git push -u origin master
```

Your GitHub profile will now show the contribution heatmap for the past years!

## Project Architecture

```
src/main/java/com/github/historybuilder/
├── Application.java                 # CLI entry point
├── GitHistoryBuilder.java          # Main orchestrator
├── config/
│   ├── HistoryConfig.java          # Configuration model
│   ├── ConfigurationLoader.java    # YAML/JSON loader
│   └── ConfigurationValidator.java # Configuration validation
├── generator/
│   ├── ContributionGenerator.java  # Interface
│   ├── RandomContributionGenerator.java
│   ├── IntelligentContributionGenerator.java
│   ├── CommitMessageGenerator.java
│   └── FileContentGenerator.java
├── git/
│   └── GitRepositoryManager.java   # Git operations
├── model/
│   ├── Author.java
│   ├── Commit.java
│   └── DistributionPattern.java
├── pattern/
│   └── TimePatternCalculator.java  # Pattern algorithms
├── tracking/
│   ├── ProgressTracker.java        # Progress tracking
│   └── ExecutionReport.java        # Execution summary
└── util/
    ├── DateUtil.java
    └── RandomUtil.java
```

## Examples

### Example 1: Basic 5-Year History

```bash
java -jar github-history-builder-1.0.0.jar
```

### Example 2: Very Active Contributor

```bash
java -jar github-history-builder-1.0.0.jar \
  --years 7 \
  --min-commits 5 \
  --max-commits 15 \
  --probability 0.7
```

### Example 3: Casual Contributor

```bash
java -jar github-history-builder-1.0.0.jar \
  --years 5 \
  --min-commits 1 \
  --max-commits 3 \
  --probability 0.2
```

### Example 4: From Configuration File

```bash
java -jar github-history-builder-1.0.0.jar --config aggressive-config.yaml
```

## Testing

Run the test suite:

```bash
mvn test
```

## Troubleshooting

### "Failed to create repository directory"
Ensure you have write permissions to the target directory.

### "Git is not initialized"
Make sure Git is installed and in your PATH.

### Large repository size
The repository can become large with many commits. Use reasonable parameters:
- Reduce `commits_per_day_max`
- Reduce `contribution_probability`
- Consider using shorter `years_back`

## Logs

Execution logs are written to:
- Console: Real-time progress
- File: `github-history-builder.log` in the current directory

## License

This project is provided for educational purposes. Use responsibly.

## Contributing

Contributions are welcome! Feel free to submit issues or pull requests.

## FAQ

**Q: Will GitHub detect this as fake activity?**
A: Possibly. GitHub has fraud detection systems. This tool is best used for educational/testing purposes.

**Q: Can I use this on someone else's account?**
A: No. Only use on your own accounts for testing purposes.

**Q: How many commits can I generate?**
A: Up to millions. 5 years × 365 days × 10 commits/day = ~18,000 commits is typical.

**Q: Can I customize commit messages?**
A: Yes! The application generates realistic messages. See `CommitMessageGenerator.java`.

**Q: What if I want to modify the generated repository?**
A: It's just a normal Git repository. You can modify, add files, change commits, etc.

---

**Made with ❤️ for educational purposes**

