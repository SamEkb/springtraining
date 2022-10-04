package ru.skilanov.spring.сommandlinerunners;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.skilanov.spring.service.api.QuestionService;


@Component
public class TestRunner implements CommandLineRunner {
    private final QuestionService questionService;

    public TestRunner(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public void run(String... args) {
        questionService.launchTest();
    }
}
