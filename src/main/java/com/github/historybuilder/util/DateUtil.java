package com.github.historybuilder.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Utility class for date operations.
 */
public class DateUtil {

    /**
     * Returns the number of days between two dates (inclusive).
     */
    public static long daysBetween(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }

    /**
     * Checks if the given date is a weekend (Saturday or Sunday).
     */
    public static boolean isWeekend(LocalDate date) {
        int dayOfWeek = date.getDayOfWeek().getValue();
        return dayOfWeek == 6 || dayOfWeek == 7; // Saturday = 6, Sunday = 7
    }

    /**
     * Checks if the given date is a weekday.
     */
    public static boolean isWeekday(LocalDate date) {
        return !isWeekend(date);
    }

    /**
     * Gets the month of the year (1-12).
     */
    public static int getMonth(LocalDate date) {
        return date.getMonthValue();
    }

    /**
     * Gets the season for the given date.
     * Winter: 12, 1, 2
     * Spring: 3, 4, 5
     * Summer: 6, 7, 8
     * Fall: 9, 10, 11
     */
    public static String getSeason(LocalDate date) {
        int month = date.getMonthValue();
        if (month == 12 || month == 1 || month == 2) {
            return "WINTER";
        } else if (month >= 3 && month <= 5) {
            return "SPRING";
        } else if (month >= 6 && month <= 8) {
            return "SUMMER";
        } else {
            return "FALL";
        }
    }
}

