package ru.skilanov.spring.dao.api;

import ru.skilanov.spring.model.Author;

import java.util.List;

public interface AuthorDao {

    void insert(Author author);

    Author getById(long id);

    List<Author> getAll();

    void deleteById(long id);
}
