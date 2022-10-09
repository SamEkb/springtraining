package ru.skilanov.spring.dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.skilanov.spring.model.Question;
import ru.skilanov.spring.dao.reader.FileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionDaoImpl implements QuestionDao {
    private final FileReader fileReader;

    public QuestionDaoImpl(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    @Override
    public List<Question> getAll(int questionNumber) {
        List<Question> questions = new ArrayList<>();

        try (BufferedReader file = fileReader.readFile()) {
            CSVParser records = CSVFormat.DEFAULT.parse(file);
            var test = records.getRecords();

            for (int i = 0; i < test.size(); i++) {
                CSVRecord record = test.get(i);
                if (i < questionNumber) {
                    var question = new Question(record.get(0), record.get(1));
                    questions.add(question);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return questions;
    }
}
