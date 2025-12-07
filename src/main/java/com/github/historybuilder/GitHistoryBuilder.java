package com.github.historybuilder;

import com.github.historybuilder.config.HistoryConfig;
import com.github.historybuilder.config.ConfigurationValidator;
import com.github.historybuilder.generator.*;
import com.github.historybuilder.git.GitRepositoryManager;
import com.github.historybuilder.model.Commit;
import com.github.historybuilder.model.DistributionPattern;
import com.github.historybuilder.tracking.ExecutionReport;
import com.github.historybuilder.tracking.ProgressTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Main orchestrator for GitHub history generation.
 */
public class GitHistoryBuilder {
    private static final Logger logger = LoggerFactory.getLogger(GitHistoryBuilder.class);

    private HistoryConfig config;
    private ExecutionReport report;

    public GitHistoryBuilder(HistoryConfig config) {
        this.config = config;
        this.report = new ExecutionReport();
    }

    /**
     * Builds the GitHub history according to configuration.
     */
    public ExecutionReport build() {
        long startTime = System.currentTimeMillis();

        try {
            logger.info("Starting GitHub History Builder");
            logger.info("Configuration: {}", config);

            // Validate configuration
            ConfigurationValidator.validate(config);

            // Generate commits
            logger.info("Generating commits using {} pattern", config.getPattern());
            List<Commit> commits = generateCommits();

            report.setTotalCommitsGenerated(commits.size());
            report.setStartDate(config.getStartDate());
            report.setEndDate(config.getEndDate());

            if (config.isDryRun()) {
                logger.info("DRY RUN MODE: Not creating repository");
                logger.info("Would create {} commits", commits.size());
            } else {
                // Create repository
                GitRepositoryManager repoManager = new GitRepositoryManager();
                repoManager.initializeRepository(config);

                try {
                    // Write commits
                    repoManager.writeCommits(commits);

                    report.setRepositoryPath(repoManager.getRepositoryDirectory().getAbsolutePath());
                    logger.info("Repository created at: {}", report.getRepositoryPath());
                } finally {
                    repoManager.close();
                }
            }

            report.setSuccess(true);

        } catch (Exception e) {
            logger.error("Error building GitHub history", e);
            report.setSuccess(false);
            report.setErrorMessage(e.getMessage());
        } finally {
            long endTime = System.currentTimeMillis();
            report.setExecutionTimeMs(endTime - startTime);
        }

        return report;
    }

    /**
     * Generates commits based on the configured pattern.
     */
    private List<Commit> generateCommits() {
        ContributionGenerator generator = createGenerator(config.getPattern());
        return generator.generateCommits(config);
    }

    /**
     * Creates the appropriate generator based on pattern.
     */
    private ContributionGenerator createGenerator(DistributionPattern pattern) {
        switch (pattern) {
            case RANDOM:
                return new RandomContributionGenerator();
            case INTELLIGENT:
            case SEASONAL:
            case WEEKDAY_BIASED:
            default:
                return new IntelligentContributionGenerator();
        }
    }

    /**
     * Gets the execution report.
     */
    public ExecutionReport getReport() {
        return report;
    }
}

