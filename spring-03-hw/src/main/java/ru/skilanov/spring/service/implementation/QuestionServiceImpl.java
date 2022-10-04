package ru.skilanov.spring.service.implementation;

import org.springframework.stereotype.Service;
import ru.skilanov.spring.config.AppConfig;
import ru.skilanov.spring.dao.QuestionDao;
import ru.skilanov.spring.model.Question;
import ru.skilanov.spring.model.Student;
import ru.skilanov.spring.service.api.AnswerService;
import ru.skilanov.spring.service.api.QuestionService;
import ru.skilanov.spring.service.api.StudentService;

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
        questionDao.getAll(appConfig.getQuestionsNumber())
                .forEach(
                        (it) -> ioService.printMessage(it.getQuestion())
                );
    }

    @Override
    public void launchTest() {
        List<Question> questions = questionDao.getAll(appConfig.getQuestionsNumber());
        Student student = createStudent();
        executeTesting(questions);

        String studentName = studentService.getFullName(student);
        int correctAnswersNumber = countCorrectAnswers(questions);

        ioService.printLocalizedMessage("test.user.fullname");
        ioService.printMessage(studentName);
        ioService.printLocalizedMessage("test.question.user.correct.answers");
        ioService.printMessage(String.valueOf(correctAnswersNumber));
    }

    private Student createStudent() {
        ioService.printLocalizedMessage("test.user.firstname");
        String firstName = ioService.scanMessage();
        ioService.printLocalizedMessage("test.user.lastname");
        String lastName = ioService.scanMessage();
        return studentService.create(firstName, lastName);
    }

    private void executeTesting(List<Question> questions) {
        questions.forEach(question -> {
            ioService.printLocalizedMessage("test.question.title");
            ioService.printMessage(question.getQuestion());
            ioService.printLocalizedMessage("test.answer.title");
            answerService.addAnswer(ioService.scanMessage());
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
