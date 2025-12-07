package com.github.historybuilder.generator;

import com.github.historybuilder.util.RandomUtil;

/**
 * Generates realistic commit messages.
 */
public class CommitMessageGenerator {

    private static final String[] FEATURES = {
        "Add new feature",
        "Implement user authentication",
        "Add API endpoint",
        "Create database migration",
        "Add validation",
        "Implement caching",
        "Add logging",
        "Create utility function",
        "Add configuration option",
        "Implement search functionality"
    };

    private static final String[] FIXES = {
        "Fix bug in",
        "Fix null pointer exception",
        "Fix memory leak",
        "Fix race condition",
        "Fix timeout issue",
        "Fix edge case",
        "Fix validation error",
        "Fix authentication issue",
        "Fix performance issue",
        "Fix data parsing"
    };

    private static final String[] REFACTORS = {
        "Refactor",
        "Simplify",
        "Optimize",
        "Clean up",
        "Reorganize",
        "Restructure",
        "Improve code quality",
        "Extract method",
        "Remove duplication",
        "Update dependencies"
    };

    private static final String[] DOCS = {
        "Update documentation",
        "Add README",
        "Add comments",
        "Update API docs",
        "Add examples",
        "Update changelog",
        "Add usage guide",
        "Fix typo in docs",
        "Add contributing guidelines",
        "Add license"
    };

    private static final String[] MODULES = {
        "in user module",
        "in auth module",
        "in database module",
        "in API module",
        "in config module",
        "in utils module",
        "in service module",
        "in controller module",
        "in model module",
        "in helper module"
    };

    /**
     * Generates a random commit message.
     */
    public static String generateCommitMessage() {
        int messageType = RandomUtil.nextInt(0, 3);

        switch (messageType) {
            case 0: // Feature
                return FEATURES[RandomUtil.nextInt(0, FEATURES.length - 1)] +
                       " " + MODULES[RandomUtil.nextInt(0, MODULES.length - 1)];
            case 1: // Fix
                return FIXES[RandomUtil.nextInt(0, FIXES.length - 1)] +
                       " " + MODULES[RandomUtil.nextInt(0, MODULES.length - 1)];
            case 2: // Refactor
                return REFACTORS[RandomUtil.nextInt(0, REFACTORS.length - 1)] +
                       " " + MODULES[RandomUtil.nextInt(0, MODULES.length - 1)];
            case 3: // Docs
                return DOCS[RandomUtil.nextInt(0, DOCS.length - 1)];
            default:
                return "Update code";
        }
    }

    /**
     * Generates a commit message with emoji prefix for variety.
     */
    public static String generateCommitMessageWithEmoji() {
        String[] emojis = {"✨", "🐛", "♻️", "📝", "🔧", "⚡", "📦", "🚀"};
        String emoji = emojis[RandomUtil.nextInt(0, emojis.length - 1)];
        return emoji + " " + generateCommitMessage();
    }
}

