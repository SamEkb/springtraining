package ru.skilanov.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.skilanov.spring.dto.GenreDto;
import ru.skilanov.spring.exception.ObjectDoesNotExistException;
import ru.skilanov.spring.mapper.GenreMapper;
import ru.skilanov.spring.model.Genre;
import ru.skilanov.spring.repository.GenreRepository;
import ru.skilanov.spring.service.api.GenreService;

import java.util.List;
import java.util.stream.Collectors;

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
    public GenreDto get(long id) {
        Genre genre = genreRepository.findById(id).orElseThrow(ObjectDoesNotExistException::new);
        return GenreMapper.INSTANCE.entityToDto(genre);
    }

    @Override
    public List<GenreDto> getAll() {
        return genreRepository.findAll().stream()
                .map(GenreMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(long id) {
        Genre genre = genreRepository.findById(id).orElseThrow(ObjectDoesNotExistException::new);
        genreRepository.delete(genre);
    }
}
