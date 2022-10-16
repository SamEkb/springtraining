package ru.skilanov.spring.service.api;

import ru.skilanov.spring.model.Genre;

import java.util.List;

public interface GenreService {
    void create(String name);

    Genre get(long id);

    List<Genre> getAll();

    void delete(long id);
}
