package ru.skilanov.spring.service;

import ru.skilanov.spring.model.Student;

public interface StudentService {

    Student create(String firstName, String lastName);

    String getFullName(Student student);
}
