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
import ru.skilanov.spring.model.Book;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Books dao test")
@DataMongoTest
@Testcontainers
class BookRepositoryTest {

    public static final String DEFAULT_ID_ONE = "1";
    public static final String DEFAULT_ID_TWO = "2";
    public static final String EXPECTED_ID = "3";
    public static final String BOOK_TITLE_KARENINA = "Anna Karenina";
    public static final String BOOK_TITLE_WAR_AND_PEACE = "Voina I Mir";
    public static final String BOOK_TITLE_VII = "Vii";
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Container
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @DisplayName("Add book to db")
    @Test
    void shouldInsertBook() {
        var expectedBook = new Book(BOOK_TITLE_KARENINA,
                authorRepository.findById(DEFAULT_ID_ONE).orElse(null),
                genreRepository.findById(DEFAULT_ID_ONE).orElse(null));
        bookRepository.save(expectedBook);
        Optional<Book> actualBook = bookRepository.findById(EXPECTED_ID);
        actualBook.ifPresent(it -> assertThat(it.getTitle()).isEqualTo(expectedBook.getTitle()));
    }

    @DisplayName("Update book to db")
    @Test
    void shouldUpdateBook() {
        var updatedBook = new Book(DEFAULT_ID_ONE, BOOK_TITLE_KARENINA,
                authorRepository.findById(DEFAULT_ID_ONE).orElse(null),
                genreRepository.findById(DEFAULT_ID_ONE).orElse(null));
        bookRepository.save(updatedBook);

        Book foundBook = bookRepository.findById(DEFAULT_ID_ONE)
                .orElseThrow(ObjectDoesNotExistException::new);

        assertThat(foundBook.getTitle()).isEqualTo(BOOK_TITLE_KARENINA);
    }

    @DisplayName("Returns expected book")
    @Test
    void shouldReturnExpectedBookById() {
        Book expectedBook = new Book(DEFAULT_ID_ONE, BOOK_TITLE_WAR_AND_PEACE,
                authorRepository.findById(DEFAULT_ID_ONE).orElse(null),
                genreRepository.findById(DEFAULT_ID_ONE).orElse(null));

        Optional<Book> actualBook = bookRepository.findById(expectedBook.getId());
        actualBook.ifPresent(it -> assertThat(it.getTitle()).isEqualTo(expectedBook.getTitle()));
    }

    @DisplayName("Deletes expected book by id")
    @Test
    void shouldCorrectDeleteBookById() {
        Book bookForDeleting = bookRepository.findAll().get(0);

        bookRepository.delete(bookForDeleting);

        Book deletedBook = bookRepository.findById(bookForDeleting.getId())
                .orElse(null);

        assertThat(deletedBook).isNull();
    }

    @DisplayName("Returns expected books list")
    @Test
    void shouldReturnExpectedBooksList() {
        int actualPersonListSize = bookRepository.findAll().size();
        assertThat(actualPersonListSize)
                .isEqualTo(bookRepository.count());
    }
}