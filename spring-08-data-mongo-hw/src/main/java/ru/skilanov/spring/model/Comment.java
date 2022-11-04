package ru.skilanov.spring.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Objects;

@Document(collection = "comments")
public class Comment {

    @Id
    private String id;

    private String description;

    @DocumentReference
    private Book book;

    public Comment() {
    }

    public Comment(String description) {
        this.description = description;
    }

    public Comment(String description, Book book) {
        this.description = description;
        this.book = book;
    }

    public Comment(String id, String description, Book book) {
        this.id = id;
        this.description = description;
        this.book = book;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(description, comment.description) && Objects.equals(book, comment.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, book);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", book=" + book +
                '}';
    }
}
