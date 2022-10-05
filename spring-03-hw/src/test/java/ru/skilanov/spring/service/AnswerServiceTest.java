package ru.skilanov.spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.skilanov.spring.service.api.AnswerService;
import ru.skilanov.spring.service.api.QuestionService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AnswerServiceTest {

    public static final String LONDON_ANSWER = "London";

    public static final String DUBLIN_ANSWER = "Dublin";

    @MockBean
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @Test
    public void whenAddAnswerItAdded() {
        assertTrue(answerService.addAnswer(LONDON_ANSWER));
    }

    @Test
    public void whenGetAnswerItReturns() {
        answerService.addAnswer(LONDON_ANSWER);
        String result = answerService.getAnswer(0);
        assertEquals(result, LONDON_ANSWER);
    }

    @Test
    public void whenCheckRightAnswerReturnTrue() {
        assertTrue(answerService.checkAnswer(LONDON_ANSWER, LONDON_ANSWER));
    }

    @Test
    public void whenCheckWrongAnswerReturnFalse() {
        assertFalse(answerService.checkAnswer(LONDON_ANSWER, DUBLIN_ANSWER));
    }

    @Test
    public void whenCheckBlankAnswerReturnFalse() {
        assertFalse(answerService.checkAnswer(LONDON_ANSWER, ""));
    }
}
