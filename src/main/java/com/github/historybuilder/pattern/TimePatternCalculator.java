package com.github.historybuilder.pattern;

import com.github.historybuilder.util.DateUtil;
import java.time.LocalDate;

/**
 * Calculates time patterns for contribution distribution.
 */
public class TimePatternCalculator {

    /**
     * Gets the intensity multiplier for a given date.
     * This affects how likely commits are to occur on that day.
     * Returns a value between 0.0 and 2.0.
     */
    public static double getIntensityMultiplier(LocalDate date) {
        double multiplier = 1.0;

        // Weekday/weekend bias
        if (DateUtil.isWeekend(date)) {
            multiplier *= 0.5; // 50% less likely on weekends
        } else {
            multiplier *= 1.2; // 20% more likely on weekdays
        }

        // Seasonal adjustment
        String season = DateUtil.getSeason(date);
        switch (season) {
            case "WINTER":
                multiplier *= 0.8; // Slightly lower in winter
                break;
            case "SPRING":
                multiplier *= 1.1; // Slightly higher in spring
                break;
            case "SUMMER":
                multiplier *= 0.7; // Lower in summer (vacation)
                break;
            case "FALL":
                multiplier *= 1.3; // Higher in fall (back to work)
                break;
        }

        // Cap the multiplier
        return Math.min(multiplier, 2.0);
    }

    /**
     * Gets a burst multiplier for creating occasional concentrated activity days.
     * Returns a value between 0.0 and 3.0 for burst days.
     */
    public static double getBurstMultiplier(LocalDate date, double burstChance) {
        // Create pseudo-random burst days based on date
        int dayOfYear = date.getDayOfYear();
        int hash = (dayOfYear * 17 + 13) % 100;

        if (hash < (burstChance * 100)) {
            return 2.0; // Double commits on burst days
        }
        return 1.0;
    }
}

