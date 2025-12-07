package com.github.historybuilder.model;

/**
 * Enum representing different contribution distribution patterns.
 */
public enum DistributionPattern {
    RANDOM("Completely random distribution"),
    INTELLIGENT("Weekday bias with seasonal variations"),
    SEASONAL("Strong seasonal variations"),
    WEEKDAY_BIASED("Heavy weekday bias, light weekends");

    private final String description;

    DistributionPattern(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

