package com.github.historybuilder;

import com.github.historybuilder.config.HistoryConfig;
import com.github.historybuilder.config.ConfigurationLoader;
import com.github.historybuilder.tracking.ExecutionReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.commandline.Command;
import picocli.commandline.Option;
import picocli.commandline.CommandLine;

import java.time.LocalDate;
import java.util.concurrent.Callable;

/**
 * Command-line application for GitHub history generation.
 */
@Command(name = "github-history-builder",
        description = "Generates fake GitHub contribution history spanning multiple years",
        version = "1.0.0",
        mixinStandardHelpOptions = true)
public class Application implements Callable<Integer> {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Option(names = {"-c", "--config"}, description = "Path to configuration file (YAML or JSON)")
    private String configPath;

    @Option(names = {"-y", "--years"}, description = "Years back to generate contributions (default: 5)")
    private Integer yearsBack = 5;

    @Option(names = {"-p", "--probability"}, description = "Probability of commits on any day (0.0-1.0, default: 0.4)")
    private Double probability = 0.4;

    @Option(names = {"-m", "--min-commits"}, description = "Minimum commits per day (default: 1)")
    private Integer minCommits = 1;

    @Option(names = {"-M", "--max-commits"}, description = "Maximum commits per day (default: 5)")
    private Integer maxCommits = 5;

    @Option(names = {"-r", "--repository"}, description = "Path where repository will be created (default: ./generated-github-repo)")
    private String repositoryPath = "./generated-github-repo";

    @Option(names = {"-n", "--name"}, description = "Author name (default: Contribution Bot)")
    private String authorName = "Contribution Bot";

    @Option(names = {"-e", "--email"}, description = "Author email (default: bot@example.com)")
    private String authorEmail = "bot@example.com";

    @Option(names = {"--dry-run"}, description = "Preview without creating repository")
    private boolean dryRun = false;

    @Option(names = {"-v", "--verbose"}, description = "Verbose output")
    private boolean verbose = false;

    @Override
    public Integer call() throws Exception {
        HistoryConfig config;

        // Load configuration
        if (configPath != null) {
            if (configPath.endsWith(".yaml") || configPath.endsWith(".yml")) {
                config = ConfigurationLoader.loadFromYaml(configPath);
            } else if (configPath.endsWith(".json")) {
                config = ConfigurationLoader.loadFromJson(configPath);
            } else {
                System.err.println("Unsupported config file format. Use .yaml or .json");
                return 1;
            }
        } else {
            // Create config from command line options
            config = new HistoryConfig();
            config.setYearsBack(yearsBack);
            config.setContributionProbability(probability);
            config.setCommitsPerDayMin(minCommits);
            config.setCommitsPerDayMax(maxCommits);
            config.setRepositoryPath(repositoryPath);
            config.setAuthorName(authorName);
            config.setAuthorEmail(authorEmail);
            config.setDryRun(dryRun);
            config.setVerbose(verbose);
            config.setEndDate(LocalDate.now());
            config.setStartDate(config.getEndDate().minusYears(yearsBack));
        }

        // Build history
        GitHistoryBuilder builder = new GitHistoryBuilder(config);
        ExecutionReport report = builder.build();

        // Print report
        report.printSummary();

        if (report.isSuccess()) {
            System.out.println("\n✓ GitHub history successfully generated!");
            System.out.println("Next steps:");
            System.out.println("  1. cd " + (repositoryPath != null ? repositoryPath : "./generated-github-repo"));
            System.out.println("  2. Create a new repository on GitHub");
            System.out.println("  3. git remote add origin <your-repo-url>");
            System.out.println("  4. git push -u origin master");
            return 0;
        } else {
            System.err.println("\n✗ Failed to generate GitHub history");
            if (report.getErrorMessage() != null) {
                System.err.println("Error: " + report.getErrorMessage());
            }
            return 1;
        }
    }

    /**
     * Main entry point.
     */
    public static void main(String[] args) {
        int exitCode = new CommandLine(new Application())
                .setExecutionExceptionHandler((ex, cmd, parseResult) -> {
                    logger.error("Execution failed", ex);
                    ex.printStackTrace();
                    return 1;
                })
                .execute(args);

        System.exit(exitCode);
    }
}

