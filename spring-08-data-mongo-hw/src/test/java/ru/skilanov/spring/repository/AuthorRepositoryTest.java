package ru.skilanov.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import ru.skilanov.spring.exception.ObjectDoesNotExistException;
import ru.skilanov.spring.model.Author;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Authors dao test")
@DataMongoTest
@Testcontainers
public class AuthorRepositoryTest {
    public static final String DEFAULT_ID_ONE = "1";
    public static final String DEFAULT_ID_TWO = "2";
    public static final String EXPECTED_AUTHOR_DOSTOEVSKY = "Dostoevsky";
    public static final String DEFAULT_AUTHOR_TOLSTOY = "Tolstoy";
    public static final String DEFAULT_AUTHOR_GOGOL = "Gogol";
    public static final String EXPECTED_ID = "3";

    @Autowired
    private AuthorRepository authorRepository;

    @Container
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

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
        Author actualAuthorList = authorRepository.findAll().get(0);

        authorRepository.delete(actualAuthorList);

        Author deletedAuthor = authorRepository.findById(actualAuthorList.getId())
                .orElse(null);

        assertThat(deletedAuthor).isNull();
    }

    @DisplayName("Returns expected authors list")
    @Test
    void shouldReturnExpectedAuthorsList() {
        int actualPersonListSize = authorRepository.findAll().size();
        assertThat(actualPersonListSize)
                .isEqualTo(authorRepository.count());
    }
}
