package ru.skilanov.spring.service.api;

public interface AnswerService {

    boolean addAnswer(String answer);

    String getAnswer(int answerNumber);

    boolean checkAnswer(String rightAnswer, String answer);
}
