package ru.skilanov.spring.service.api;

import ru.skilanov.spring.model.Comment;

import java.util.List;

public interface CommentService {
    void create(String name, long bookId);

    Comment get(long id);

    void delete(long id);

    List<Comment> getByBookId(long id);
}
