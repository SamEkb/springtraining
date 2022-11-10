package ru.skilanov.spring.service.api;

import ru.skilanov.spring.model.Book;

import java.util.List;

public interface BookService {
    void create(String title, String authorId, String genreId);

    void update(Book book);

    Book get(String id);

    List<Book> getAll();

    void delete(String id);
}
