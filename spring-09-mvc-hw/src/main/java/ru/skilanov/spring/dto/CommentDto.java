package ru.skilanov.spring.dto;

import ru.skilanov.spring.model.Book;

public class CommentDto {

    private long id;

    private String description;

    private BookDto book;

    public CommentDto() {
    }

    public CommentDto(String description, BookDto book) {
        this.description = description;
        this.book = book;
    }

    public CommentDto(long id, String description, BookDto book) {
        this.id = id;
        this.description = description;
        this.book = book;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BookDto getBook() {
        return book;
    }

    public void setBook(BookDto book) {
        this.book = book;
    }
}
