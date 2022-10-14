package ru.skilanov.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;
import ru.skilanov.spring.config.TestContainersConfig;
import ru.skilanov.spring.dao.impl.AuthorDaoImpl;
import ru.skilanov.spring.dao.impl.BookDaoImpl;
import ru.skilanov.spring.model.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("Authors dao test")
@Import({AuthorDaoImpl.class, BookDaoImpl.class})
public class AuthorDaoImplTest extends TestContainersConfig {
    public static final int DEFAULT_ID_ONE = 1;
    public static final int DEFAULT_ID_TWO = 2;
    public static final String EXPECTED_AUTHOR_DOSTOEVSKY = "Dostoevsky";
    public static final String DEFAULT_AUTHOR_TOLSTOY = "Tolstoy";
    public static final String DEFAULT_AUTHOR_GOGOL = "Gogol";
    public static final int EXPECTED_ID = 3;

    @Autowired
    private AuthorDaoImpl authorDao;

    @Autowired
    private BookDaoImpl bookDao;

    @DisplayName("Add author to db")
    @Transactional
    @Test
    void shouldInsertAuthor() {
        var expectedAuthor = new Author(EXPECTED_AUTHOR_DOSTOEVSKY);
        authorDao.insert(expectedAuthor);
        Author actualAuthor = authorDao.getById(EXPECTED_ID);
        assertThat(actualAuthor.getName()).isEqualTo(expectedAuthor.getName());
    }

    @DisplayName("Returns expected author")
    @Test
    void shouldReturnExpectedAuthorById() {
        Author expectedAuthor = new Author(DEFAULT_ID_ONE, DEFAULT_AUTHOR_TOLSTOY);
        Author actualAuthor = authorDao.getById(expectedAuthor.getId());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("Deletes expected author by id")
    @Transactional
    @Test
    void shouldCorrectDeleteAuthorById() {
        assertThatCode(() -> authorDao.getById(DEFAULT_ID_ONE))
                .doesNotThrowAnyException();

        bookDao.deleteById(DEFAULT_ID_ONE);
        authorDao.deleteById(DEFAULT_ID_ONE);

        assertThatThrownBy(() -> authorDao.getById(DEFAULT_ID_ONE))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("Returns expected authors list")
    @Test
    void shouldReturnExpectedAuthorsList() {
        var expectedAuthorTolstoy = new Author(DEFAULT_ID_ONE, DEFAULT_AUTHOR_TOLSTOY);
        var expectedAuthorGogol = new Author(DEFAULT_ID_TWO, DEFAULT_AUTHOR_GOGOL);
        List<Author> actualAuthorList = authorDao.getAll();
        assertThat(actualAuthorList)
                .containsExactlyInAnyOrder(expectedAuthorTolstoy, expectedAuthorGogol);
    }
}
