package ru.skilanov.spring.dao.api;

import ru.skilanov.spring.model.Comment;

import java.util.Optional;

public interface CommentDao {

    Comment save(Comment author);

    Optional<Comment> getById(long id);

    void deleteById(long id);
}
