package com.github.historybuilder.generator;

import com.github.historybuilder.model.Commit;
import com.github.historybuilder.config.HistoryConfig;
import java.time.LocalDate;
import java.util.List;

/**
 * Interface for contribution generation strategies.
 */
public interface ContributionGenerator {

    /**
     * Generates commits for the given date range according to configuration.
     */
    List<Commit> generateCommits(HistoryConfig config);

    /**
     * Gets the number of commits to create for a given date.
     */
    int getCommitsForDate(LocalDate date, HistoryConfig config);
}

