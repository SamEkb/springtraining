package ru.skilanov.spring.service.api;

import ru.skilanov.spring.model.Book;

import java.util.List;

public interface BookService {
    void create(String title, long authorId, long genreId);

    void update(Book book);

    Book get(long id);

    List<Book> getAll();

    void delete(long id);
}
