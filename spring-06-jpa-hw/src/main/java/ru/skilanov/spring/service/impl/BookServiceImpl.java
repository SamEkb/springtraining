package ru.skilanov.spring.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skilanov.spring.dao.api.BookDao;
import ru.skilanov.spring.exception.BookDoesNotExistException;
import ru.skilanov.spring.model.Author;
import ru.skilanov.spring.model.Book;
import ru.skilanov.spring.model.Genre;
import ru.skilanov.spring.service.api.AuthorService;
import ru.skilanov.spring.service.api.BookService;
import ru.skilanov.spring.service.api.GenreService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookServiceImpl(BookDao bookDao, AuthorService authorService, GenreService genreService) {
        this.bookDao = bookDao;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @Transactional
    @Override
    public void create(String title, long authorId, long genreId) {
        Author author = authorService.get(authorId);
        Genre genre = genreService.get(genreId);
        var book = new Book(title, author, genre);
        bookDao.save(book);
    }

    @Transactional
    @Override
    public void update(Book book) {
        bookDao.save(book);
    }

    @Override
    public Book get(long id) {
        return bookDao.getById(id).orElseThrow(BookDoesNotExistException::new);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Transactional
    @Override
    public void delete(long id) {
        Book book = bookDao.getById(id).orElseThrow(BookDoesNotExistException::new);
        bookDao.delete(book);
    }
}
