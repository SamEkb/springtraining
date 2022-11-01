package ru.skilanov.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.skilanov.spring.model.Book;
import ru.skilanov.spring.repository.AuthorRepository;
import ru.skilanov.spring.repository.BookRepository;
import ru.skilanov.spring.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Books dao test")
@DataJpaTest
class BookRepositoryTest {

    public static final long DEFAULT_ID_ONE = 1;
    public static final long DEFAULT_ID_TWO = 2;
    public static final long EXPECTED_ID = 3;
    public static final String BOOK_TITLE_KARENINA = "Anna Karenina";
    public static final String BOOK_TITLE_WAR_AND_PEACE = "Voina I Mir";
    public static final String BOOK_TITLE_VII = "Vii";
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private TestEntityManager testEntityManager;

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
        testEntityManager.detach(updatedBook);

        Book foundBook = testEntityManager.find(Book.class, DEFAULT_ID_ONE);

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
        Book bookForDeleting = testEntityManager.find(Book.class, DEFAULT_ID_ONE);

        bookRepository.delete(bookForDeleting);

        Book deletedBook = testEntityManager.find(Book.class, DEFAULT_ID_ONE);

        assertThat(deletedBook).isNull();
    }

    @DisplayName("Returns expected books list")
    @Test
    void shouldReturnExpectedBooksList() {
        var expectedBookWaP = new Book(DEFAULT_ID_ONE, BOOK_TITLE_WAR_AND_PEACE,
                authorRepository.findById(DEFAULT_ID_ONE).orElse(null),
                genreRepository.findById(DEFAULT_ID_ONE).orElse(null));
        var expectedBookVii = new Book(DEFAULT_ID_TWO, BOOK_TITLE_VII,
                authorRepository.findById(DEFAULT_ID_TWO).orElse(null),
                genreRepository.findById(DEFAULT_ID_TWO).orElse(null));
        List<Book> actualPersonList = bookRepository.findAll();
        assertThat(actualPersonList)
                .containsExactlyInAnyOrder(expectedBookWaP, expectedBookVii);
    }
}