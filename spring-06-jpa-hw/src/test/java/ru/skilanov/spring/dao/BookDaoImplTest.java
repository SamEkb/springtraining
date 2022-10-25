package ru.skilanov.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.skilanov.spring.PostgresSQLContainerInitializer;
import ru.skilanov.spring.dao.impl.AuthorDaoImpl;
import ru.skilanov.spring.dao.impl.BookDaoImpl;
import ru.skilanov.spring.dao.impl.GenreDaoImpl;
import ru.skilanov.spring.model.Book;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Books dao test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({BookDaoImpl.class, AuthorDaoImpl.class, GenreDaoImpl.class})
class BookDaoImplTest implements PostgresSQLContainerInitializer {

    public static final long DEFAULT_ID_ONE = 1;
    public static final long DEFAULT_ID_TWO = 2;
    public static final long EXPECTED_ID = 3;
    public static final String BOOK_TITLE_KARENINA = "Anna Karenina";
    public static final String BOOK_TITLE_WAR_AND_PEACE = "Voina I Mir";
    public static final String BOOK_TITLE_VII = "Vii";
    @Autowired
    private BookDaoImpl bookDao;
    @Autowired
    private AuthorDaoImpl authorDao;
    @Autowired
    private GenreDaoImpl genreDao;
    @Autowired
    private TestEntityManager testEntityManager;

    @DisplayName("Add book to db")
    @Test
    void shouldInsertBook() {
        var expectedBook = new Book(BOOK_TITLE_KARENINA,
                authorDao.getById(DEFAULT_ID_ONE).orElse(null),
                genreDao.getById(DEFAULT_ID_ONE).orElse(null));
        bookDao.save(expectedBook);
        Optional<Book> actualBook = bookDao.getById(EXPECTED_ID);
        actualBook.ifPresent(it -> assertThat(it.getTitle()).isEqualTo(expectedBook.getTitle()));
    }

    @DisplayName("Update book to db")
    @Test
    void shouldUpdateBook() {
        var updatedBook = new Book(DEFAULT_ID_ONE, BOOK_TITLE_KARENINA,
                authorDao.getById(DEFAULT_ID_ONE).orElse(null),
                genreDao.getById(DEFAULT_ID_ONE).orElse(null));
        bookDao.save(updatedBook);
        testEntityManager.detach(updatedBook);

        Book foundBook = testEntityManager.find(Book.class, DEFAULT_ID_ONE);

        assertThat(foundBook.getTitle()).isEqualTo(BOOK_TITLE_KARENINA);
    }

    @DisplayName("Returns expected book")
    @Test
    void shouldReturnExpectedBookById() {
        Book expectedBook = new Book(DEFAULT_ID_ONE, BOOK_TITLE_WAR_AND_PEACE,
                authorDao.getById(DEFAULT_ID_ONE).orElse(null),
                genreDao.getById(DEFAULT_ID_ONE).orElse(null));
        Optional<Book> actualBook = bookDao.getById(expectedBook.getId());
        actualBook.ifPresent(it -> assertThat(it).usingRecursiveComparison().isEqualTo(expectedBook));
    }

    @DisplayName("Deletes expected book by id")
    @Test
    void shouldCorrectDeleteBookById() {
        bookDao.deleteById(DEFAULT_ID_ONE);

        Book deletedBook = testEntityManager.find(Book.class, DEFAULT_ID_ONE);

        assertThat(deletedBook).isNull();
    }

    @DisplayName("Returns expected books list")
    @Test
    void shouldReturnExpectedBooksList() {
        var expectedBookWaP = new Book(DEFAULT_ID_ONE, BOOK_TITLE_WAR_AND_PEACE,
                authorDao.getById(DEFAULT_ID_ONE).orElse(null),
                genreDao.getById(DEFAULT_ID_ONE).orElse(null));
        var expectedBookVii = new Book(DEFAULT_ID_TWO, BOOK_TITLE_VII,
                authorDao.getById(DEFAULT_ID_TWO).orElse(null),
                genreDao.getById(DEFAULT_ID_TWO).orElse(null));
        List<Book> actualPersonList = bookDao.getAll();
        assertThat(actualPersonList)
                .containsExactlyInAnyOrder(expectedBookWaP, expectedBookVii);
    }
}