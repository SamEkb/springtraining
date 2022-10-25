package ru.skilanov.spring.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skilanov.spring.dao.api.GenreDao;
import ru.skilanov.spring.exception.GenreDoesNotExistException;
import ru.skilanov.spring.model.Genre;
import ru.skilanov.spring.service.api.GenreService;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreDao genreDao;

    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Transactional
    @Override
    public void create(String name) {
        var genre = new Genre(name);
        genreDao.save(genre);
    }

    @Override
    public Genre get(long id) {
        return genreDao.getById(id).orElseThrow(GenreDoesNotExistException::new);
    }

    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }

    @Transactional
    @Override
    public void delete(long id) {
        genreDao.deleteById(id);
    }
}
