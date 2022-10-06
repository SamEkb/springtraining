package ru.skilanov.spring.service.implementation;

import org.springframework.stereotype.Service;
import ru.skilanov.spring.model.Student;
import ru.skilanov.spring.service.api.StudentService;

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
