package com.github.historybuilder.model;

import java.time.ZonedDateTime;

/**
 * Represents a single commit with all necessary metadata.
 */
public class Commit {
    private ZonedDateTime timestamp;
    private String message;
    private Author author;
    private String filePath;
    private String fileContent;

    public Commit(ZonedDateTime timestamp, String message, Author author) {
        this.timestamp = timestamp;
        this.message = message;
        this.author = author;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    @Override
    public String toString() {
        return "Commit{" +
                "timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", author=" + author +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}

