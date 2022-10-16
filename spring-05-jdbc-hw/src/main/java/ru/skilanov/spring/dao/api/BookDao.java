package ru.skilanov.spring.dao.api;

import ru.skilanov.spring.model.Book;

import java.util.List;

public interface BookDao {

    void insert(Book book);

    void update(Book book);

    Book getById(long id);

    List<Book> getAll();

    void deleteById(long id);
}
