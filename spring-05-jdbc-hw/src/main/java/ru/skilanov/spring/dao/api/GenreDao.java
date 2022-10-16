package ru.skilanov.spring.dao.api;

import ru.skilanov.spring.model.Genre;

import java.util.List;

public interface GenreDao {

    void insert(Genre genre);

    Genre getById(long id);

    List<Genre> getAll();

    void deleteById(long id);
}
