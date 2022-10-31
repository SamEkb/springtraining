package ru.skilanov.spring.dao.api;

import ru.skilanov.spring.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    Book save(Book book);

    Optional<Book> getById(long id);

    List<Book> getAll();

    void delete(Book book);
}
