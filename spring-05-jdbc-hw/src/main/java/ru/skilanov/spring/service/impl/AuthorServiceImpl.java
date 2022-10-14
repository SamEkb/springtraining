package ru.skilanov.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.skilanov.spring.dao.api.AuthorDao;
import ru.skilanov.spring.model.Author;
import ru.skilanov.spring.service.api.AuthorService;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public void create(String name) {
        var author = new Author(name);
        authorDao.insert(author);
    }

    @Override
    public Author get(long id) {
        return authorDao.getById(id);
    }

    @Override
    public List<Author> getAll() {
        return authorDao.getAll();
    }

    @Override
    public void delete(long id) {
        authorDao.deleteById(id);
    }
}
