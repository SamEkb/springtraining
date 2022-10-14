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
import ru.skilanov.spring.dao.impl.GenreDaoImpl;
import ru.skilanov.spring.model.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("Books dao test")
@Import({BookDaoImpl.class, AuthorDaoImpl.class, GenreDaoImpl.class})
class BookDaoImplTest extends TestContainersConfig {

    public static final int DEFAULT_ID_ONE = 1;
    public static final int DEFAULT_ID_TWO = 2;
    public static final int EXPECTED_ID = 3;
    public static final String BOOK_TITLE_KARENINA = "Anna Karenina";
    public static final String BOOK_TITLE_WAR_AND_PEACE = "Voina I Mir";
    public static final String BOOK_TITLE_VII = "Vii";
    @Autowired
    private BookDaoImpl bookDao;
    @Autowired
    private AuthorDaoImpl authorDao;
    @Autowired
    private GenreDaoImpl genreDao;

    @DisplayName("Add book to db")
    @Transactional
    @Test
    void shouldInsertBook() {
        var expectedBook = new Book(BOOK_TITLE_KARENINA, authorDao.getById(DEFAULT_ID_ONE),
                genreDao.getById(DEFAULT_ID_ONE));
        bookDao.insert(expectedBook);
        Book actualBook = bookDao.getById(EXPECTED_ID);
        assertThat(actualBook.getTitle()).isEqualTo(expectedBook.getTitle());
    }

    @DisplayName("Update book to db")
    @Transactional
    @Test
    void shouldUpdateBook() {
        var updatedBook = new Book(DEFAULT_ID_ONE, BOOK_TITLE_KARENINA, authorDao.getById(DEFAULT_ID_ONE),
                genreDao.getById(DEFAULT_ID_ONE));
        bookDao.update(updatedBook);
        Book actualBook = bookDao.getById(DEFAULT_ID_ONE);
        assertThat(actualBook.getTitle()).isEqualTo(BOOK_TITLE_KARENINA);
    }

    @DisplayName("Returns expected book")
    @Test
    void shouldReturnExpectedBookById() {
        Book expectedBook = new Book(DEFAULT_ID_ONE, BOOK_TITLE_WAR_AND_PEACE, authorDao.getById(DEFAULT_ID_ONE),
                genreDao.getById(DEFAULT_ID_ONE));
        Book actualBook = bookDao.getById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Transactional
    @DisplayName("Deletes expected book by id")
    @Test
    void shouldCorrectDeleteBookById() {
        assertThatCode(() -> bookDao.getById(DEFAULT_ID_ONE))
                .doesNotThrowAnyException();

        bookDao.deleteById(DEFAULT_ID_ONE);

        assertThatThrownBy(() -> bookDao.getById(DEFAULT_ID_ONE))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("Returns expected books list")
    @Test
    void shouldReturnExpectedBooksList() {
        var expectedBookWaP = new Book(DEFAULT_ID_ONE, BOOK_TITLE_WAR_AND_PEACE, authorDao.getById(DEFAULT_ID_ONE),
                genreDao.getById(DEFAULT_ID_ONE));
        var expectedBookVii = new Book(DEFAULT_ID_TWO, BOOK_TITLE_VII, authorDao.getById(DEFAULT_ID_TWO),
                genreDao.getById(DEFAULT_ID_TWO));
        List<Book> actualPersonList = bookDao.getAll();
        assertThat(actualPersonList)
                .containsExactlyInAnyOrder(expectedBookWaP, expectedBookVii);
    }
}