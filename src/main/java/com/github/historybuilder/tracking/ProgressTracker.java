package com.github.historybuilder.tracking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Tracks progress of commit generation and repository creation.
 */
public class ProgressTracker {
    private static final Logger logger = LoggerFactory.getLogger(ProgressTracker.class);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private long totalCommits;
    private long processedCommits;
    private long startTime;

    public ProgressTracker(long totalCommits) {
        this.totalCommits = totalCommits;
        this.processedCommits = 0;
        this.startTime = System.currentTimeMillis();
    }

    /**
     * Records progress on a single commit.
     */
    public void recordProgress(int commitsBatch) {
        processedCommits += commitsBatch;

        if (processedCommits % 100 == 0 || processedCommits == totalCommits) {
            double percentage = (100.0 * processedCommits) / totalCommits;
            long elapsedMs = System.currentTimeMillis() - startTime;
            long estimatedRemainingMs = (long) ((elapsedMs * (totalCommits - processedCommits)) / processedCommits);

            long elapsedSec = elapsedMs / 1000;
            long estimatedRemainingSec = estimatedRemainingMs / 1000;

            logger.info("[{}/{} ({:.1f}%)] Processed - Elapsed: {}s, ETA: {}s",
                    processedCommits, totalCommits, percentage,
                    elapsedSec, estimatedRemainingSec);
        }
    }

    /**
     * Gets the percentage complete.
     */
    public double getPercentageComplete() {
        if (totalCommits == 0) return 0;
        return (100.0 * processedCommits) / totalCommits;
    }

    /**
     * Gets elapsed time in seconds.
     */
    public long getElapsedSeconds() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }
}

