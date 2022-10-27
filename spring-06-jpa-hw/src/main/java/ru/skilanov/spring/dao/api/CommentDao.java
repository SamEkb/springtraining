package ru.skilanov.spring.dao.api;

import ru.skilanov.spring.model.Comment;

import java.util.Optional;

public interface CommentDao {

    Comment save(Comment comment);

    Optional<Comment> getById(long id);

    void delete(Comment comment);
}
