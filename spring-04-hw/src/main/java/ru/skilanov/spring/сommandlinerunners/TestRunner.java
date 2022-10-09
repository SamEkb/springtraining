package ru.skilanov.spring.сommandlinerunners;

import org.springframework.boot.CommandLineRunner;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Component;
import ru.skilanov.spring.service.api.QuestionService;

@ShellComponent
@Component
public class TestRunner implements CommandLineRunner {
    private final QuestionService questionService;

    public TestRunner(QuestionService questionService) {
        this.questionService = questionService;
    }

    @ShellMethod(value = "Run command", key = {"r", "run"})
    @Override
    public void run(@ShellOption("r") String... args) {
        questionService.launchTest();
    }
}
