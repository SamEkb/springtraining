package ru.skilanov.spring.dao.impl;

import org.springframework.stereotype.Component;
import ru.skilanov.spring.dao.api.BookDao;
import ru.skilanov.spring.model.Book;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.FETCH;

@Component
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    private final EntityManager entityManager;

    public BookDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Book save(Book book) {
        if (book.getId() == 0) {
            entityManager.persist(book);
            return book;
        }
        return entityManager.merge(book);
    }


    @Override
    public Optional<Book> getById(long id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    @Override
    public List<Book> getAll() {
        EntityGraph<?> authorEntityGraph = entityManager.getEntityGraph("bookPropertiesEntityGraph");
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b", Book.class);
        query.setHint(FETCH.getKey(), authorEntityGraph);
        return query.getResultList();
    }

    @Override
    public void delete(Book book) {
        entityManager.remove(book);
    }
}
