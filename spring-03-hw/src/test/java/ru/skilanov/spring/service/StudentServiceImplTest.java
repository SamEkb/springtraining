package ru.skilanov.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.skilanov.spring.model.Student;
import ru.skilanov.spring.service.StudentService;
import ru.skilanov.spring.service.StudentServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentServiceImplTest {

    public static final String FIRST_NAME = "Bilbo";
    public static final String LAST_NAME = "Baggins";

    public static final String FULL_NAME = "Bilbo Baggins";
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentServiceImpl();
    }

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
