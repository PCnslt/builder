package com.github.historybuilder.util;

import java.util.Random;

/**
 * Utility class for random operations.
 */
public class RandomUtil {
    private static final Random random = new Random();

    /**
     * Returns a random integer between min (inclusive) and max (inclusive).
     */
    public static int nextInt(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("min must be <= max");
        }
        return random.nextInt((max - min) + 1) + min;
    }

    /**
     * Returns a random double between 0.0 (inclusive) and 1.0 (exclusive).
     */
    public static double nextDouble() {
        return random.nextDouble();
    }

    /**
     * Returns true with the given probability (0.0 to 1.0).
     */
    public static boolean nextBoolean(double probability) {
        if (probability < 0 || probability > 1) {
            throw new IllegalArgumentException("Probability must be between 0 and 1");
        }
        return random.nextDouble() < probability;
    }

    /**
     * Resets the random seed for testing purposes.
     */
    public static void setSeed(long seed) {
        random.setSeed(seed);
    }
}

