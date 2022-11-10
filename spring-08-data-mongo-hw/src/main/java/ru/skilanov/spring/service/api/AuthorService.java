package ru.skilanov.spring.service.api;

import ru.skilanov.spring.model.Author;

import java.util.List;

public interface AuthorService {
    void create(String name);

    Author get(String id);

    List<Author> getAll();

    void delete(String id);
}
