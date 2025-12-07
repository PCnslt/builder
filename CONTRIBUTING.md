# Contributing to GitHub History Builder

Thank you for your interest in contributing! This document provides guidelines and instructions for contributing.

## Code of Conduct

Be respectful, inclusive, and professional in all interactions.

## How to Contribute

### Reporting Issues

- Check existing issues first
- Provide clear description and steps to reproduce
- Include Java version, Maven version, OS
- Attach relevant logs if applicable

### Suggesting Enhancements

- Use GitHub issues with "[FEATURE]" prefix
- Clearly describe the enhancement
- Explain why it would be useful
- Show examples if applicable

### Code Contributions

1. **Fork** the repository
2. **Create** a feature branch: `git checkout -b feature/your-feature`
3. **Make** your changes following code style
4. **Test** your changes: `mvn test`
5. **Commit** with clear messages: `git commit -m "Add feature X"`
6. **Push** to your fork: `git push origin feature/your-feature`
7. **Create** a Pull Request

## Code Style

### Java Conventions

- Follow Google Java Style Guide
- Use 4-space indentation
- Use meaningful variable names
- Add Javadoc for public methods
- Keep classes focused and single-responsibility

### Naming Conventions

```java
// Classes: PascalCase
public class CommitMessageGenerator { }

// Methods: camelCase
public String generateMessage() { }

// Constants: CONSTANT_CASE
private static final int MAX_RETRIES = 3;

// Variables: camelCase
int totalCommits = 100;
```

### Documentation

- Add Javadoc comments to public classes and methods
- Include parameter descriptions and return types
- Add examples for complex methods
- Update README for user-facing changes

```java
/**
 * Generates a random commit message.
 * 
 * @return a randomly generated commit message
 * @see CommitMessageGenerator#generateCommitMessageWithEmoji()
 */
public static String generateCommitMessage() {
    // implementation
}
```

## Testing Requirements

All contributions must include tests:

```java
@Test
public void testFeatureName() {
    // Arrange
    HistoryConfig config = new HistoryConfig();
    config.setYearsBack(5);
    
    // Act
    ContributionGenerator generator = new RandomContributionGenerator();
    List<Commit> commits = generator.generateCommits(config);
    
    // Assert
    assertNotNull(commits);
    assertTrue(commits.size() > 0);
}
```

### Running Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=RandomContributionGeneratorTest

# Run with coverage
mvn test jacoco:report
```

### Test Coverage Goals

- Aim for 80%+ code coverage
- All public methods should be tested
- Include both happy path and error cases
- Test boundary conditions

## Project Structure

```
src/main/java/com/github/historybuilder/
├── Application.java              # CLI entry point
├── GitHistoryBuilder.java        # Main orchestrator
├── config/                       # Configuration layer
├── generator/                    # Generation strategies
├── git/                          # Git operations
├── model/                        # Data models
├── pattern/                      # Pattern algorithms
├── tracking/                     # Progress tracking
└── util/                         # Utilities
```

### When Adding Features

1. **New Generator Type**: Add to `generator/` package
   - Implement `ContributionGenerator` interface
   - Add unit tests
   - Add configuration option
   - Document in README

2. **New Pattern**: Add to `pattern/` package
   - Create pattern calculator
   - Add tests
   - Document algorithm

3. **New CLI Option**: Update `Application.java`
   - Add `@Option` annotation
   - Update help text
   - Add validation
   - Update README

## Building & Testing

```bash
# Build without running tests
mvn clean package -DskipTests

# Build with tests
mvn clean package

# Run specific test
mvn test -Dtest=ConfigurationValidatorTest

# Build with coverage report
mvn clean test jacoco:report
# View: target/site/jacoco/index.html
```

## Commit Messages

Use clear, descriptive commit messages:

```
Good:
- "Add IntelligentContributionGenerator with seasonal bias"
- "Fix timezone handling in Commit class"
- "Update README with configuration examples"

Avoid:
- "fix bug"
- "update"
- "changes"
```

## Pull Request Process

1. **Update** documentation (README, ARCHITECTURE, etc.)
2. **Add** tests for new functionality
3. **Ensure** `mvn test` passes
4. **Write** clear PR description
5. **Reference** related issues
6. **Wait** for review and feedback

### PR Template

```markdown
## Description
Brief description of changes

## Type of Change
- [ ] Bug fix
- [ ] New feature
- [ ] Documentation update

## Related Issues
Closes #123

## How to Test
Steps to test the changes

## Checklist
- [ ] Tests added/updated
- [ ] Documentation updated
- [ ] All tests pass
- [ ] Code follows style guidelines
```

## Design Principles

1. **Single Responsibility**: Each class has one job
2. **Open/Closed**: Open for extension, closed for modification
3. **Dependency Injection**: Pass dependencies, don't create them
4. **Composition over Inheritance**: Favor composition
5. **Testability**: Code should be easily testable

## Performance Considerations

- Use efficient data structures (List for sequence, Map for lookup)
- Avoid unnecessary object creation in loops
- Consider memory usage for large date ranges
- Profile before optimizing

## Security Considerations

- Validate all user inputs
- Handle exceptions gracefully
- Don't expose sensitive information
- Use secure random generation

## Documentation

### README Updates

- Update when user-facing behavior changes
- Add usage examples
- Update configuration options
- Include troubleshooting tips

### Code Documentation

- Javadoc for public APIs
- Comments for complex logic
- README for getting started

### Architecture Documentation

- Update ARCHITECTURE.md for significant changes
- Document new design patterns used
- Explain algorithm choices

## Release Process

1. Update version in pom.xml
2. Update CHANGELOG (if exists)
3. Create release branch
4. Tag release: `git tag v1.0.0`
5. Create GitHub release

## Areas for Contribution

### High Priority

- [ ] Support for multiple authors
- [ ] Co-authored commits
- [ ] More realistic commit content
- [ ] Configuration validation improvements
- [ ] Performance optimizations

### Medium Priority

- [ ] Additional distribution patterns
- [ ] Holiday adjustments
- [ ] Merge commit simulation
- [ ] Branch history

### Nice to Have

- [ ] Web UI for configuration
- [ ] Preview heatmap
- [ ] Direct GitHub API integration
- [ ] Statistics dashboard

## Getting Help

- Check existing issues
- Review documentation
- Ask in pull request comments
- Create a discussion issue

## License

By contributing, you agree that your contributions will be licensed under the same license as the project.

---

**Thank you for contributing to GitHub History Builder!** ❤️

