package ru.skilanov.spring.service.api;

import ru.skilanov.spring.dto.BookDto;
import ru.skilanov.spring.model.Book;

import java.util.List;

public interface BookService {
    void create(BookDto dto);

    void update(BookDto book);

    BookDto get(long id);

    List<BookDto> getAll();

    void delete(long id);
}
