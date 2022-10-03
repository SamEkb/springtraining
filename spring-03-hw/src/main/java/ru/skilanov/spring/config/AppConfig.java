package ru.skilanov.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@ConfigurationProperties(prefix = "application")
public class AppConfig {
    public static final String UNDERSCORE = "_";
    private static final int STRING_INDEX = 4;
    private String questionsFilePath;
    private int questionsNumber;
    private Locale locale;

    public String getQuestionsFilePath() {
        return new StringBuffer(questionsFilePath)
                .insert(questionsFilePath.length() - STRING_INDEX, UNDERSCORE + getLocale().toString())
                .toString();
    }

    public void setQuestionsFilePath(String questionsFilePath) {
        this.questionsFilePath = questionsFilePath;
    }

    public int getQuestionsNumber() {
        return questionsNumber;
    }

    public void setQuestionsNumber(int questionsNumber) {
        this.questionsNumber = questionsNumber;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
