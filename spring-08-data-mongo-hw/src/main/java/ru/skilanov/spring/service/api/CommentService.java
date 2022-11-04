package ru.skilanov.spring.service.api;

import ru.skilanov.spring.model.Comment;

import java.util.List;

public interface CommentService {
    void create(String name, String bookId);

    Comment get(String id);

    void delete(String id);

    List<Comment> getByBookId(String id);
}
