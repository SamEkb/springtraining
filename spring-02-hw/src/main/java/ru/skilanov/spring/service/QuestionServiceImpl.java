package ru.skilanov.spring.service;

import org.springframework.stereotype.Service;
import ru.skilanov.spring.config.AppConfig;
import ru.skilanov.spring.dao.QuestionDao;
import ru.skilanov.spring.model.Question;
import ru.skilanov.spring.model.Student;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;
    private final StudentService studentService;
    private final AnswerService answerService;
    private final IOServiceImpl ioService;
    private final AppConfig appConfig;

    public QuestionServiceImpl(QuestionDao questionDao, StudentService studentService, AnswerService answerService,
                               IOServiceImpl ioService, AppConfig appConfig
    ) {
        this.questionDao = questionDao;
        this.studentService = studentService;
        this.answerService = answerService;
        this.ioService = ioService;
        this.appConfig = appConfig;
    }

    @Override
    public void printQuestions() {
        questionDao.getAll(appConfig.getQuestionsNumber()).forEach((it) -> ioService.printMessage(it.getQuestion()));
    }

    @Override
    public void launchTest() {
        List<Question> questions = questionDao.getAll(appConfig.getQuestionsNumber());
        Student student = createStudent();
        executeTesting(questions);

        int correctAnswersNumber = countCorrectAnswers(questions);
        String studentName = studentService.getFullName(student);

        ioService.printMessage(String.format("Dear %s correct answers: %d", studentName, correctAnswersNumber));
    }

    private Student createStudent() {
        String firstName = ioService.scanMessage("Please write first name:");
        String lastName = ioService.scanMessage("Please write last name:");
        return studentService.create(firstName, lastName);
    }

    private void executeTesting(List<Question> questions) {
        questions.forEach(question -> {
            ioService.printMessage("Question: " + question.getQuestion());
            answerService.addAnswer(ioService.scanMessage("Answer:"));
        });
    }

    private int countCorrectAnswers(List<Question> questions) {
        int correctAnswers = 0;

        for (int i = 0; i < questions.size(); i++) {
            String userAnswer = answerService.getAnswer(i);
            if (answerService.checkAnswer(questions.get(i).getAnswer(), userAnswer)) {
                correctAnswers++;
            }
        }

        return correctAnswers;
    }
}
