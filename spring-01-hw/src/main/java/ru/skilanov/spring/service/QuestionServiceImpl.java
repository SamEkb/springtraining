package ru.skilanov.spring.service;

import ru.skilanov.spring.dao.QuestionDao;

public class QuestionServiceImpl implements QuestionService {
    private QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public void printQuestions() {
        questionDao.getAll().forEach(System.out::println);
    }
}
