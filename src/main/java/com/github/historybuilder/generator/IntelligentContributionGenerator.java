package com.github.historybuilder.generator;

import com.github.historybuilder.config.HistoryConfig;
import com.github.historybuilder.model.Author;
import com.github.historybuilder.model.Commit;
import com.github.historybuilder.pattern.TimePatternCalculator;
import com.github.historybuilder.util.RandomUtil;
import com.github.historybuilder.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * Generates commits with intelligent distribution considering weekday/weekend patterns and seasons.
 */
public class IntelligentContributionGenerator implements ContributionGenerator {
    private static final Logger logger = LoggerFactory.getLogger(IntelligentContributionGenerator.class);

    @Override
    public List<Commit> generateCommits(HistoryConfig config) {
        List<Commit> commits = new ArrayList<>();
        Author author = new Author(config.getAuthorName(), config.getAuthorEmail());

        LocalDate current = config.getStartDate();
        while (!current.isAfter(config.getEndDate())) {
            int commitCount = getCommitsForDate(current, config);

            for (int i = 0; i < commitCount; i++) {
                ZonedDateTime timestamp = current
                        .atTime(RandomUtil.nextInt(0, 23), RandomUtil.nextInt(0, 59), 0)
                        .atZone(ZoneId.systemDefault());

                Commit commit = new Commit(timestamp, CommitMessageGenerator.generateCommitMessage(), author);
                commit.setFilePath("contributions.log");
                commit.setFileContent(FileContentGenerator.generateLogLine(current));

                commits.add(commit);
            }

            current = current.plusDays(1);
        }

        logger.info("Generated {} commits using INTELLIGENT distribution", commits.size());
        return commits;
    }

    @Override
    public int getCommitsForDate(LocalDate date, HistoryConfig config) {
        double baseProb = config.getContributionProbability();

        // Apply weekday/weekend modifiers
        if (DateUtil.isWeekend(date)) {
            baseProb *= config.getWeekendProbabilityReduction();
        } else {
            baseProb *= config.getWeekdayProbabilityBoost();
        }

        // Apply intensity multiplier
        baseProb *= TimePatternCalculator.getIntensityMultiplier(date);

        // Cap probability at 1.0
        baseProb = Math.min(baseProb, 1.0);

        if (RandomUtil.nextBoolean(baseProb)) {
            // Apply burst multiplier
            double burstMultiplier = TimePatternCalculator.getBurstMultiplier(date, 0.05);
            int baseCommits = RandomUtil.nextInt(config.getCommitsPerDayMin(), config.getCommitsPerDayMax());
            return (int) (baseCommits * burstMultiplier);
        }

        return 0;
    }
}

