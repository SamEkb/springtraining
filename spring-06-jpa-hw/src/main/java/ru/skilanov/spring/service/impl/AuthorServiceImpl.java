package ru.skilanov.spring.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skilanov.spring.dao.api.AuthorDao;
import ru.skilanov.spring.exception.ObjectDoesNotExistException;
import ru.skilanov.spring.model.Author;
import ru.skilanov.spring.service.api.AuthorService;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Transactional
    @Override
    public void create(String name) {
        var author = new Author(name);
        authorDao.save(author);
    }

    @Override
    public Author get(long id) {
        return authorDao.getById(id).orElseThrow(ObjectDoesNotExistException::new);
    }

    @Override
    public List<Author> getAll() {
        return authorDao.getAll();
    }

    @Transactional
    @Override
    public void delete(long id) {
        Author author = authorDao.getById(id).orElseThrow(ObjectDoesNotExistException::new);
        authorDao.delete(author);
    }
}
