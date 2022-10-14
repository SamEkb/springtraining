package ru.skilanov.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;
import ru.skilanov.spring.config.TestContainersConfig;
import ru.skilanov.spring.dao.impl.BookDaoImpl;
import ru.skilanov.spring.dao.impl.GenreDaoImpl;
import ru.skilanov.spring.model.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("Genres dao test")
@Import({GenreDaoImpl.class, BookDaoImpl.class})
public class GenreDaoImplTest extends TestContainersConfig {
    public static final int DEFAULT_ID_ONE = 1;
    public static final int DEFAULT_ID_TWO = 2;
    public static final String DEFAULT_GENRE_DRAMA = "Drama";
    public static final String DEFAULT_GENRE_HORROR = "Horror";
    public static final String EXPECTED_GENRE = "Thriller";
    public static final int EXPECTED_ID = 3;
    @Autowired
    private GenreDaoImpl genreDao;

    @Autowired
    private BookDaoImpl bookDao;

    @DisplayName("Add genre to db")
    @Transactional
    @Test
    void shouldInsertGenre() {
        var expectedGenre = new Genre(EXPECTED_GENRE);
        genreDao.insert(expectedGenre);
        Genre actualGenre = genreDao.getById(EXPECTED_ID);
        assertThat(actualGenre.getName()).isEqualTo(expectedGenre.getName());
    }

    @DisplayName("Returns expected genre")
    @Test
    void shouldReturnExpectedGenreById() {
        Genre expectedGenre = new Genre(DEFAULT_ID_ONE, DEFAULT_GENRE_DRAMA);
        Genre actualGenre = genreDao.getById(expectedGenre.getId());
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("Deletes expected genre by id")
    @Transactional
    @Test
    void shouldCorrectDeleteGenreById() {
        assertThatCode(() -> genreDao.getById(DEFAULT_ID_ONE))
                .doesNotThrowAnyException();
        bookDao.deleteById(DEFAULT_ID_ONE);
        genreDao.deleteById(DEFAULT_ID_ONE);

        assertThatThrownBy(() -> genreDao.getById(DEFAULT_ID_ONE))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("Returns expected genre list")
    @Test
    void shouldReturnExpectedGenreList() {
        var expectedGenreDrama = new Genre(DEFAULT_ID_ONE, DEFAULT_GENRE_DRAMA);
        var expectedGenreHorror = new Genre(DEFAULT_ID_TWO, DEFAULT_GENRE_HORROR);
        List<Genre> actualPersonList = genreDao.getAll();
        assertThat(actualPersonList)
                .containsExactlyInAnyOrder(expectedGenreDrama, expectedGenreHorror);
    }
}
