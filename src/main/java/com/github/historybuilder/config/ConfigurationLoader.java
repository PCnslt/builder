package com.github.historybuilder.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Loads configuration from YAML, JSON, or properties files.
 */
public class ConfigurationLoader {
    private static final Logger logger = LoggerFactory.getLogger(ConfigurationLoader.class);

    /**
     * Loads configuration from a YAML file.
     */
    public static HistoryConfig loadFromYaml(String filePath) throws IOException {
        logger.info("Loading configuration from YAML: {}", filePath);
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        HistoryConfig config = mapper.readValue(new File(filePath), HistoryConfig.class);

        // Set default dates if not provided
        if (config.getEndDate() == null) {
            config.setEndDate(LocalDate.now());
        }
        if (config.getStartDate() == null) {
            config.setStartDate(config.getEndDate().minusYears(config.getYearsBack()));
        }

        logger.info("Configuration loaded successfully: {}", config);
        return config;
    }

    /**
     * Loads configuration from a JSON file.
     */
    public static HistoryConfig loadFromJson(String filePath) throws IOException {
        logger.info("Loading configuration from JSON: {}", filePath);
        ObjectMapper mapper = new ObjectMapper();
        HistoryConfig config = mapper.readValue(new File(filePath), HistoryConfig.class);

        if (config.getEndDate() == null) {
            config.setEndDate(LocalDate.now());
        }
        if (config.getStartDate() == null) {
            config.setStartDate(config.getEndDate().minusYears(config.getYearsBack()));
        }

        logger.info("Configuration loaded successfully: {}", config);
        return config;
    }

    /**
     * Creates a default configuration.
     */
    public static HistoryConfig createDefault() {
        logger.info("Creating default configuration");
        HistoryConfig config = new HistoryConfig();
        config.setEndDate(LocalDate.now());
        config.setStartDate(config.getEndDate().minusYears(config.getYearsBack()));
        return config;
    }
}

