package ru.skilanov.spring.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
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

    public Comment(long id, String description, Book book) {
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
