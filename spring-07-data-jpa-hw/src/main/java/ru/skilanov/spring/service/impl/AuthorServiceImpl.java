package ru.skilanov.spring.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skilanov.spring.repository.AuthorRepository;
import ru.skilanov.spring.exception.ObjectDoesNotExistException;
import ru.skilanov.spring.model.Author;
import ru.skilanov.spring.service.api.AuthorService;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void create(String name) {
        var author = new Author(name);
        authorRepository.save(author);
    }

    @Override
    public Author get(long id) {
        return authorRepository.findById(id).orElseThrow(ObjectDoesNotExistException::new);
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public void delete(long id) {
        Author author = authorRepository.findById(id).orElseThrow(ObjectDoesNotExistException::new);
        authorRepository.delete(author);
    }
}
