package ru.skilanov.spring.dao.reader;

import org.springframework.stereotype.Component;
import ru.skilanov.spring.config.AppConfig;
import ru.skilanov.spring.exceptions.FileDoesNotExistException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
public class FileReaderImpl implements FileReader {
    private final AppConfig appConfig;

    public FileReaderImpl(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Override
    public BufferedReader readFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream file = classLoader.getResourceAsStream(appConfig.getQuestionsFilePath());
        if (file == null) {
            throw new FileDoesNotExistException("File does not exist");
        }
        InputStreamReader streamReader = new InputStreamReader(file, StandardCharsets.UTF_8);

        return new BufferedReader(streamReader);
    }
}
