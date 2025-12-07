package com.github.historybuilder.generator;

import com.github.historybuilder.config.HistoryConfig;
import com.github.historybuilder.model.Author;
import com.github.historybuilder.model.Commit;
import com.github.historybuilder.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * Generates commits with completely random distribution.
 */
public class RandomContributionGenerator implements ContributionGenerator {
    private static final Logger logger = LoggerFactory.getLogger(RandomContributionGenerator.class);

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

        logger.info("Generated {} commits using RANDOM distribution", commits.size());
        return commits;
    }

    @Override
    public int getCommitsForDate(LocalDate date, HistoryConfig config) {
        // Pure random - each day has a chance to have commits
        if (RandomUtil.nextBoolean(config.getContributionProbability())) {
            return RandomUtil.nextInt(config.getCommitsPerDayMin(), config.getCommitsPerDayMax());
        }
        return 0;
    }
}

