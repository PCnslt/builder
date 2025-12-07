package com.github.historybuilder.config;

import com.github.historybuilder.model.DistributionPattern;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

/**
 * Configuration for GitHub history generation.
 */
public class HistoryConfig {
    @JsonProperty("author_name")
    private String authorName = "Contribution Bot";

    @JsonProperty("author_email")
    private String authorEmail = "bot@example.com";

    @JsonProperty("years_back")
    private int yearsBack = 5;

    @JsonProperty("start_date")
    private LocalDate startDate;

    @JsonProperty("end_date")
    private LocalDate endDate;

    @JsonProperty("pattern")
    private DistributionPattern pattern = DistributionPattern.INTELLIGENT;

    @JsonProperty("commits_per_day_min")
    private int commitsPerDayMin = 1;

    @JsonProperty("commits_per_day_max")
    private int commitsPerDayMax = 5;

    @JsonProperty("contribution_probability")
    private double contributionProbability = 0.4; // 40% of days have commits

    @JsonProperty("weekday_probability_boost")
    private double weekdayProbabilityBoost = 1.5; // 50% more likely on weekdays

    @JsonProperty("weekend_probability_reduction")
    private double weekendProbabilityReduction = 0.5; // 50% less likely on weekends

    @JsonProperty("repository_path")
    private String repositoryPath = "./generated-github-repo";

    @JsonProperty("dry_run")
    private boolean dryRun = false;

    @JsonProperty("verbose")
    private boolean verbose = false;

    // Getters and Setters
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public int getYearsBack() {
        return yearsBack;
    }

    public void setYearsBack(int yearsBack) {
        this.yearsBack = yearsBack;
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

    public DistributionPattern getPattern() {
        return pattern;
    }

    public void setPattern(DistributionPattern pattern) {
        this.pattern = pattern;
    }

    public int getCommitsPerDayMin() {
        return commitsPerDayMin;
    }

    public void setCommitsPerDayMin(int commitsPerDayMin) {
        this.commitsPerDayMin = commitsPerDayMin;
    }

    public int getCommitsPerDayMax() {
        return commitsPerDayMax;
    }

    public void setCommitsPerDayMax(int commitsPerDayMax) {
        this.commitsPerDayMax = commitsPerDayMax;
    }

    public double getContributionProbability() {
        return contributionProbability;
    }

    public void setContributionProbability(double contributionProbability) {
        this.contributionProbability = contributionProbability;
    }

    public double getWeekdayProbabilityBoost() {
        return weekdayProbabilityBoost;
    }

    public void setWeekdayProbabilityBoost(double weekdayProbabilityBoost) {
        this.weekdayProbabilityBoost = weekdayProbabilityBoost;
    }

    public double getWeekendProbabilityReduction() {
        return weekendProbabilityReduction;
    }

    public void setWeekendProbabilityReduction(double weekendProbabilityReduction) {
        this.weekendProbabilityReduction = weekendProbabilityReduction;
    }

    public String getRepositoryPath() {
        return repositoryPath;
    }

    public void setRepositoryPath(String repositoryPath) {
        this.repositoryPath = repositoryPath;
    }

    public boolean isDryRun() {
        return dryRun;
    }

    public void setDryRun(boolean dryRun) {
        this.dryRun = dryRun;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    @Override
    public String toString() {
        return "HistoryConfig{" +
                "authorName='" + authorName + '\'' +
                ", authorEmail='" + authorEmail + '\'' +
                ", yearsBack=" + yearsBack +
                ", pattern=" + pattern +
                ", repositoryPath='" + repositoryPath + '\'' +
                '}';
    }
}

