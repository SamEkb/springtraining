package ru.skilanov.spring.service.api;

import ru.skilanov.spring.dto.GenreDto;
import ru.skilanov.spring.model.Genre;

import java.util.List;

public interface GenreService {
    void create(String name);

    GenreDto get(long id);

    List<GenreDto> getAll();

    void delete(long id);
}
