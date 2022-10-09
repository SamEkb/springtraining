package ru.skilanov.spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.skilanov.spring.model.Student;
import ru.skilanov.spring.service.api.QuestionService;
import ru.skilanov.spring.service.api.StudentService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StudentServiceImplTest {

    public static final String FIRST_NAME = "Bilbo";
    public static final String LAST_NAME = "Baggins";
    public static final String FULL_NAME = "Bilbo Baggins";

    @MockBean
    private QuestionService questionService;
    @Autowired
    private StudentService studentService;

    @Test
    public void whenCreateStudentThenReturnTrue() {
        Student student = studentService.create(FIRST_NAME, LAST_NAME);

        assertEquals(student.getFirstName(), FIRST_NAME);
    }

    @Test
    public void whenGetFullNameItReturns() {
        Student student = studentService.create(FIRST_NAME, LAST_NAME);
        String studentFullName = studentService.getFullName(student);
        assertEquals(studentFullName, FULL_NAME);
    }
}
