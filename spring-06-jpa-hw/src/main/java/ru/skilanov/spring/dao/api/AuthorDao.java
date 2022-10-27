package ru.skilanov.spring.dao.api;

import ru.skilanov.spring.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    Author save(Author author);

    Optional<Author> getById(long id);

    List<Author> getAll();

    void delete(Author author);
}
