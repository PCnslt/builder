package com.github.historybuilder.tracking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

/**
 * Reports execution statistics and summary.
 */
public class ExecutionReport {
    private static final Logger logger = LoggerFactory.getLogger(ExecutionReport.class);

    private int totalCommitsGenerated;
    private int totalDaysWithCommits;
    private LocalDate startDate;
    private LocalDate endDate;
    private long executionTimeMs;
    private String repositoryPath;
    private boolean success;
    private String errorMessage;

    public ExecutionReport() {
    }

    /**
     * Prints a formatted summary report.
     */
    public void printSummary() {
        logger.info("=== EXECUTION REPORT ===");
        logger.info("Status: {}", success ? "SUCCESS" : "FAILED");

        if (!success && errorMessage != null) {
            logger.error("Error: {}", errorMessage);
        }

        logger.info("Total Commits Generated: {}", totalCommitsGenerated);
        logger.info("Total Days with Commits: {}", totalDaysWithCommits);
        logger.info("Date Range: {} to {}", startDate, endDate);
        logger.info("Execution Time: {}ms ({} seconds)", executionTimeMs, executionTimeMs / 1000);
        logger.info("Repository Path: {}", repositoryPath);
        logger.info("======================");
    }

    // Getters and Setters
    public int getTotalCommitsGenerated() {
        return totalCommitsGenerated;
    }

    public void setTotalCommitsGenerated(int totalCommitsGenerated) {
        this.totalCommitsGenerated = totalCommitsGenerated;
    }

    public int getTotalDaysWithCommits() {
        return totalDaysWithCommits;
    }

    public void setTotalDaysWithCommits(int totalDaysWithCommits) {
        this.totalDaysWithCommits = totalDaysWithCommits;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public long getExecutionTimeMs() {
        return executionTimeMs;
    }

    public void setExecutionTimeMs(long executionTimeMs) {
        this.executionTimeMs = executionTimeMs;
    }

    public String getRepositoryPath() {
        return repositoryPath;
    }

    public void setRepositoryPath(String repositoryPath) {
        this.repositoryPath = repositoryPath;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

