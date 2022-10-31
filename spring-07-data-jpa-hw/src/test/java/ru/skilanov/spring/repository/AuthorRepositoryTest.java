package ru.skilanov.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.skilanov.spring.model.Author;
import ru.skilanov.spring.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Authors dao test")
@DataJpaTest
public class AuthorRepositoryTest {
    public static final long DEFAULT_ID_ONE = 1;
    public static final long DEFAULT_ID_TWO = 2;
    public static final String EXPECTED_AUTHOR_DOSTOEVSKY = "Dostoevsky";
    public static final String DEFAULT_AUTHOR_TOLSTOY = "Tolstoy";
    public static final String DEFAULT_AUTHOR_GOGOL = "Gogol";
    public static final long EXPECTED_ID = 3;

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @DisplayName("Add author to db")
    @Test
    void shouldInsertAuthor() {
        var expectedAuthor = new Author(EXPECTED_AUTHOR_DOSTOEVSKY);
        authorRepository.save(expectedAuthor);
        Optional<Author> actualAuthor = authorRepository.findById(EXPECTED_ID);
        actualAuthor.ifPresent(it -> assertThat(it.getName()).isEqualTo(expectedAuthor.getName()));
    }

    @DisplayName("Returns expected author")
    @Test
    void shouldReturnExpectedAuthorById() {
        Author expectedAuthor = new Author(DEFAULT_ID_ONE, DEFAULT_AUTHOR_TOLSTOY);
        Optional<Author> actualAuthor = authorRepository.findById(expectedAuthor.getId());
        actualAuthor.ifPresent(it -> assertThat(it).usingRecursiveComparison().isEqualTo(expectedAuthor));
    }

    @DisplayName("Deletes expected author by id")
    @Test
    void shouldCorrectDeleteAuthorById() {
        Author authorForDeleting = testEntityManager.find(Author.class, DEFAULT_ID_ONE);

        authorRepository.delete(authorForDeleting);

        Author deletedAuthor = testEntityManager.find(Author.class, DEFAULT_ID_ONE);

        assertThat(deletedAuthor).isNull();
    }

    @DisplayName("Returns expected authors list")
    @Test
    void shouldReturnExpectedAuthorsList() {
        var expectedAuthorTolstoy = new Author(DEFAULT_ID_ONE, DEFAULT_AUTHOR_TOLSTOY);
        var expectedAuthorGogol = new Author(DEFAULT_ID_TWO, DEFAULT_AUTHOR_GOGOL);
        List<Author> actualAuthorList = authorRepository.findAll();
        assertThat(actualAuthorList)
                .containsExactlyInAnyOrder(expectedAuthorTolstoy, expectedAuthorGogol);
    }
}
