package ru.skilanov.spring.service.implementation;

import org.springframework.stereotype.Service;
import ru.skilanov.spring.service.api.AnswerService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    private final List<String> answers = new ArrayList<>();

    @Override
    public boolean addAnswer(String answer) {
        return answers.add(answer);
    }

    @Override
    public String getAnswer(int answerNumber) {
        return answers.get(answerNumber);
    }

    @Override
    public boolean checkAnswer(String rightAnswer, String userAnswer) {
        if (userAnswer.isBlank() || rightAnswer.isBlank()) {
            return false;
        }
        return userAnswer.equalsIgnoreCase(rightAnswer);
    }
}
