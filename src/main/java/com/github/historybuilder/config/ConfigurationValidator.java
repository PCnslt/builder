package com.github.historybuilder.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validates configuration values.
 */
public class ConfigurationValidator {
    private static final Logger logger = LoggerFactory.getLogger(ConfigurationValidator.class);

    /**
     * Validates the configuration.
     * @throws IllegalArgumentException if configuration is invalid
     */
    public static void validate(HistoryConfig config) {
        logger.info("Validating configuration...");

        if (config == null) {
            throw new IllegalArgumentException("Configuration cannot be null");
        }

        if (config.getAuthorName() == null || config.getAuthorName().trim().isEmpty()) {
            throw new IllegalArgumentException("Author name cannot be empty");
        }

        if (config.getAuthorEmail() == null || config.getAuthorEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Author email cannot be empty");
        }

        if (config.getYearsBack() < 1) {
            throw new IllegalArgumentException("Years back must be at least 1");
        }

        if (config.getCommitsPerDayMin() < 0) {
            throw new IllegalArgumentException("Commits per day minimum cannot be negative");
        }

        if (config.getCommitsPerDayMax() < config.getCommitsPerDayMin()) {
            throw new IllegalArgumentException("Commits per day max must be >= min");
        }

        if (config.getContributionProbability() < 0 || config.getContributionProbability() > 1) {
            throw new IllegalArgumentException("Contribution probability must be between 0 and 1");
        }

        if (config.getWeekdayProbabilityBoost() < 0) {
            throw new IllegalArgumentException("Weekday probability boost cannot be negative");
        }

        if (config.getWeekendProbabilityReduction() < 0) {
            throw new IllegalArgumentException("Weekend probability reduction cannot be negative");
        }

        if (config.getStartDate() != null && config.getEndDate() != null) {
            if (config.getStartDate().isAfter(config.getEndDate())) {
                throw new IllegalArgumentException("Start date cannot be after end date");
            }
        }

        logger.info("Configuration validation passed");
    }
}

