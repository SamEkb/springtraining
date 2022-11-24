package ru.skilanov.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.skilanov.spring.dto.AuthorDto;
import ru.skilanov.spring.exception.ObjectDoesNotExistException;
import ru.skilanov.spring.mapper.AuthorMapper;
import ru.skilanov.spring.model.Author;
import ru.skilanov.spring.repository.AuthorRepository;
import ru.skilanov.spring.service.api.AuthorService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void create(String name) {
        Author author = new Author(name);
        authorRepository.save(author);
    }

    @Override
    public AuthorDto get(long id) {
        Author author = authorRepository.findById(id).orElseThrow(ObjectDoesNotExistException::new);
        return AuthorMapper.INSTANCE.entityToDto(author);
    }

    @Override
    public List<AuthorDto> getAll() {
        return authorRepository.findAll().stream()
                .map(AuthorMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(long id) {
        Author author = authorRepository.findById(id).orElseThrow(ObjectDoesNotExistException::new);
        authorRepository.delete(author);
    }
}
