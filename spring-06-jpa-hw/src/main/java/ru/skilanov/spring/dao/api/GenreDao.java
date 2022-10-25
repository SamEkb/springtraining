package ru.skilanov.spring.dao.api;

import ru.skilanov.spring.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {

    Genre save(Genre genre);

    Optional<Genre> getById(long id);

    List<Genre> getAll();

    void deleteById(long id);
}
