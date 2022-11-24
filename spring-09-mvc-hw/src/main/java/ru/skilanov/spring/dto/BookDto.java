package ru.skilanov.spring.dto;

import java.util.List;

public class BookDto {

    private long id;

    private String title;

    private AuthorDto author;

    private GenreDto genre;

    private List<CommentDto> comments;

    public BookDto() {
    }

    public BookDto(String title, AuthorDto author, GenreDto genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public BookDto(String title, AuthorDto author, GenreDto genre, List<CommentDto> comments) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.comments = comments;
    }

    public BookDto(long id, String title, AuthorDto author, GenreDto genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public BookDto(long id, String title, AuthorDto author, GenreDto genre, List<CommentDto> comments) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.comments = comments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
    }

    public GenreDto getGenre() {
        return genre;
    }

    public void setGenre(GenreDto genre) {
        this.genre = genre;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }
}
