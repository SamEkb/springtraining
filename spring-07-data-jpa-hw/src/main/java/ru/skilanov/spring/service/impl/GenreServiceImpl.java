package ru.skilanov.spring.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skilanov.spring.repository.GenreRepository;
import ru.skilanov.spring.exception.ObjectDoesNotExistException;
import ru.skilanov.spring.model.Genre;
import ru.skilanov.spring.service.api.GenreService;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public void create(String name) {
        var genre = new Genre(name);
        genreRepository.save(genre);
    }

    @Override
    public Genre get(long id) {
        return genreRepository.findById(id).orElseThrow(ObjectDoesNotExistException::new);
    }

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    @Override
    public void delete(long id) {
        Genre genre = genreRepository.findById(id).orElseThrow(ObjectDoesNotExistException::new);
        genreRepository.delete(genre);
    }
}
