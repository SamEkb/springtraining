package ru.skilanov.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.skilanov.spring.dao.api.AuthorDao;
import ru.skilanov.spring.dao.api.BookDao;
import ru.skilanov.spring.dao.api.GenreDao;
import ru.skilanov.spring.model.Book;
import ru.skilanov.spring.service.api.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public BookServiceImpl(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public void create(String title, long authorId, long genreId) {
        var author = authorDao.getById(authorId);
        var genre = genreDao.getById(genreId);
        var book = new Book(title, author, genre);
        bookDao.insert(book);
    }

    @Override
    public void update(Book book) {
        bookDao.update(book);
    }

    @Override
    public Book get(long id) {
        return bookDao.getById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public void delete(long id) {
        bookDao.deleteById(id);
    }
}
