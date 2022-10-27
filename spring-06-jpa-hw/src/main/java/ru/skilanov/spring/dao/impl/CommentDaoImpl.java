package ru.skilanov.spring.dao.impl;

import org.springframework.stereotype.Repository;
import ru.skilanov.spring.dao.api.CommentDao;
import ru.skilanov.spring.model.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@Repository
public class CommentDaoImpl implements CommentDao {
    @PersistenceContext
    private final EntityManager entityManager;

    public CommentDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == 0) {
            entityManager.persist(comment);
            return comment;
        }
        return entityManager.merge(comment);
    }

    @Override
    public Optional<Comment> getById(long id) {
        return Optional.ofNullable(entityManager.find(Comment.class, id));
    }

    @Override
    public void delete(Comment comment) {
        entityManager.remove(comment);
    }
}
