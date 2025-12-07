package com.github.historybuilder.generator;

import com.github.historybuilder.util.RandomUtil;

/**
 * Generates file content for commits.
 */
public class FileContentGenerator {

    /**
     * Generates simple text content for a contribution file.
     */
    public static String generateContent() {
        String[] contents = {
            "// Code contribution\nfunction doSomething() { return true; }",
            "// Bug fix\nvar fixed = true;",
            "// Feature implementation\nclass Feature { }",
            "# Documentation update\n\nThis is an important update.",
            "TODO: Implement feature X\n// In progress",
            "// Performance improvement\noptimized_code = 1;",
            "// Refactoring\nvar x = 10;\nvar y = 20;",
            "// Unit tests\nfunction test() { }\n",
            "Configuration: { enabled: true }",
            "// Fix for issue #42\nbugFixed = true;"
        };

        return contents[RandomUtil.nextInt(0, contents.length - 1)];
    }

    /**
     * Generates a line to append to a contribution log file.
     */
    public static String generateLogLine(java.time.LocalDate date) {
        return String.format("Contribution on %s at %02d:%02d:%02d%n",
                date,
                RandomUtil.nextInt(0, 23),
                RandomUtil.nextInt(0, 59),
                RandomUtil.nextInt(0, 59));
    }
}

