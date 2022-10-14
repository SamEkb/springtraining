package ru.skilanov.spring.dao.impl;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.skilanov.spring.dao.api.BookDao;
import ru.skilanov.spring.model.Author;
import ru.skilanov.spring.model.Book;
import ru.skilanov.spring.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoImpl implements BookDao {

    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public void insert(Book book) {
        namedParameterJdbcOperations.update("insert into books (title, author_id, genre_id) " +
                        "values (:title, :author_id, :genre_id)",
                Map.of(
                        "title", book.getTitle(),
                        "author_id", book.getAuthor().getId(),
                        "genre_id", book.getGenre().getId()
                )
        );
    }

    @Override
    public void update(Book book) {
        namedParameterJdbcOperations.update(
                "update books set id = :id, title = :title, author_id = :author_id," +
                        " genre_id = :genre_id where id = :id",
                Map.of("id", book.getId(),
                        "title", book.getTitle(),
                        "author_id", book.getAuthor().getId(),
                        "genre_id", book.getGenre().getId()));
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select b.id as bookId, b.title as bookTitle," +
                        " a.id as authorId, a.name as authorName, " +
                        "g.id as genreId, g.name as genreName" +
                        " from books b " +
                        "join authors a on a.id = b.author_id" +
                        " join genres g on g.id = b.genre_id" +
                        " where b.id = :id"
                , params, new BookMapper()
        );
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query(
                "select b.id as bookId, b.title as bookTitle," +
                        " a.id as authorId, a.name as authorName, " +
                        "g.id as genreId, g.name as genreName" +
                        " from books b " +
                        "join authors a on a.id = b.author_id" +
                        " join genres g on g.id = b.genre_id",
                new BookMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from books where id = :id", params
        );
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("bookId");
            String title = resultSet.getString("bookTitle");
            long authorId = resultSet.getLong("authorId");
            String authorName = resultSet.getString("authorName");
            long genreId = resultSet.getLong("genreId");
            String genreName = resultSet.getString("genreName");
            var author = new Author(authorId, authorName);
            var genre = new Genre(genreId, genreName);
            return new Book(id, title, author, genre);
        }
    }
}
