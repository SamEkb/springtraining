package ru.skilanov.spring.dao;

import ru.skilanov.spring.model.Question;

import java.io.InputStream;
import java.util.List;

public interface QuestionDao {

    List<Question> getAll();
}
