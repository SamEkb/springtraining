package ru.skilanov.spring.service.api;

import ru.skilanov.spring.dto.AuthorDto;
import ru.skilanov.spring.model.Author;

import java.util.List;

public interface AuthorService {
    void create(String name);

    AuthorDto get(long id);

    List<AuthorDto> getAll();

    void delete(long id);
}
