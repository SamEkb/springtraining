package ru.skilanov.spring.service;

import org.springframework.stereotype.Service;
import ru.skilanov.spring.model.Student;

@Service
public class StudentServiceImpl implements StudentService {
    @Override
    public Student create(String firstName, String lastName) {
        return new Student(firstName, lastName);
    }

    @Override
    public String getFullName(Student student) {
        return String.format("%s %s", student.getFirstName(), student.getLastName());
    }
}
