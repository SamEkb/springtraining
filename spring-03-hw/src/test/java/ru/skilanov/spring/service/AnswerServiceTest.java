package ru.skilanov.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.skilanov.spring.service.api.AnswerService;
import ru.skilanov.spring.service.implementation.AnswerServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

public class AnswerServiceTest {

    public static final String LONDON_ANSWER = "London";

    public static final String DUBLIN_ANSWER = "Dublin";

    private AnswerService answerService;

    @BeforeEach
    void setUp() {
        answerService = new AnswerServiceImpl();
    }

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
