package ru.skilanov.spring.dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import ru.skilanov.spring.model.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {
    private String resourceFilePath;

    public QuestionDaoImpl() {

    }

    public QuestionDaoImpl(String resourceFilePath) {
        this.resourceFilePath = resourceFilePath;
    }

    @Override
    public List<Question> getAll() {
        List<Question> questions = new ArrayList<>();
        InputStream file = readFile();
        try (
                InputStreamReader streamReader = new InputStreamReader(file, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader)
        ) {
            CSVParser records = CSVFormat.DEFAULT.parse(reader);
            for (var record : records) {
                var q = new Question(record.get(0), record.get(1));
                questions.add(q);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return questions;
    }

    private InputStream readFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        return classLoader.getResourceAsStream(resourceFilePath);
    }
}
