package ru.skilanov.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;
import ru.skilanov.spring.model.Genre;
import ru.skilanov.spring.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Genres dao test")
@DataJpaTest
public class GenreRepositoryTest {
    public static final long DEFAULT_ID_ONE = 1;
    public static final long DEFAULT_ID_TWO = 2;
    public static final String DEFAULT_GENRE_DRAMA = "Drama";
    public static final String DEFAULT_GENRE_HORROR = "Horror";
    public static final String EXPECTED_GENRE = "Thriller";
    public static final long EXPECTED_ID = 3;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @DisplayName("Add genre to db")
    @Transactional
    @Test
    void shouldInsertGenre() {
        var expectedGenre = new Genre(EXPECTED_GENRE);
        genreRepository.save(expectedGenre);
        Optional<Genre> actualGenre = genreRepository.findById(EXPECTED_ID);
        actualGenre.ifPresent(it -> assertThat(it.getName()).isEqualTo(expectedGenre.getName()));
    }

    @DisplayName("Returns expected genre")
    @Test
    void shouldReturnExpectedGenreById() {
        Genre expectedGenre = new Genre(DEFAULT_ID_ONE, DEFAULT_GENRE_DRAMA);
        Optional<Genre> actualGenre = genreRepository.findById(expectedGenre.getId());
        actualGenre.ifPresent(it -> assertThat(it).usingRecursiveComparison().isEqualTo(expectedGenre));
    }

    @DisplayName("Deletes expected genre by id")
    @Transactional
    @Test
    void shouldCorrectDeleteGenreById() {
        Genre genreForDeleting = testEntityManager.find(Genre.class, DEFAULT_ID_ONE);

        genreRepository.delete(genreForDeleting);

        Genre deletedGenre = testEntityManager.find(Genre.class, DEFAULT_ID_ONE);

        assertThat(deletedGenre).isNull();
    }

    @DisplayName("Returns expected genre list")
    @Test
    void shouldReturnExpectedGenreList() {
        var expectedGenreDrama = new Genre(DEFAULT_ID_ONE, DEFAULT_GENRE_DRAMA);
        var expectedGenreHorror = new Genre(DEFAULT_ID_TWO, DEFAULT_GENRE_HORROR);
        List<Genre> actualPersonList = genreRepository.findAll();
        assertThat(actualPersonList)
                .containsExactlyInAnyOrder(expectedGenreDrama, expectedGenreHorror);
    }
}
