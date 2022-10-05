package ru.skilanov.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:application.properties")
public class AppConfig {
    private final String resourceFilePath;
    private final int questionsNumber;

    public AppConfig(@Value("${file.resource.path}") String resourceFilePath,
                     @Value("${questions.number}") int questionsNumber
    ) {
        this.resourceFilePath = resourceFilePath;
        this.questionsNumber = questionsNumber;
    }

    public String getResourceFilePath() {
        return resourceFilePath;
    }

    public int getQuestionsNumber() {
        return questionsNumber;
    }
}
