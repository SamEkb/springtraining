package ru.skilanov.spring.dao.impl;

import org.springframework.stereotype.Repository;
import ru.skilanov.spring.dao.api.GenreDao;
import ru.skilanov.spring.model.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreDaoImpl implements GenreDao {

    @PersistenceContext
    private final EntityManager entityManager;

    public GenreDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Genre save(Genre genre) {
        if (genre.getId() == 0) {
            entityManager.persist(genre);
            return genre;
        }
        return entityManager.merge(genre);
    }

    @Override
    public Optional<Genre> getById(long id) {
        return Optional.ofNullable(entityManager.find(Genre.class, id));
    }

    @Override
    public List<Genre> getAll() {
        TypedQuery<Genre> query = entityManager.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    public void delete(Genre genre) {
        entityManager.remove(genre);
    }
}
