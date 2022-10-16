package ru.skilanov.spring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.skilanov.spring.dao.api.BookDao;
import ru.skilanov.spring.model.Author;
import ru.skilanov.spring.model.Book;
import ru.skilanov.spring.model.Genre;
import ru.skilanov.spring.service.api.AuthorService;
import ru.skilanov.spring.service.api.GenreService;
import ru.skilanov.spring.service.impl.BookServiceImpl;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@DisplayName("Books service test")
@SpringBootTest(classes = {BookServiceImpl.class})
public class BookServiceImplTest {

    public static final String BOOK_TITLE_KARENINA = "Anna Karenina";
    public static final String BOOK_TITLE_WAR_AND_PEACE = "Voina I Mir";
    public static final int ONE = 1;
    public static final int TWO = 2;

    @MockBean
    private BookDao bookDao;
    @MockBean
    private AuthorService authorService;
    @MockBean
    private GenreService genreService;
    @Autowired
    private BookServiceImpl bookService;

    @DisplayName("Creat book")
    @Test
    public void whenCreateBookThenItCreated() {
        Book book1 = new Book(ONE, BOOK_TITLE_KARENINA, new Author(""), new Genre(""));
        doNothing().when(bookDao).insert(book1);
        bookService.create(BOOK_TITLE_KARENINA, ONE, ONE);
        verify(bookDao, times(ONE)).insert(any());
    }

    @DisplayName("Update book")
    @Test
    public void whenUpdateBookThenItUpdated() {
        Book book1 = new Book(ONE, BOOK_TITLE_KARENINA, new Author(""), new Genre(""));
        doNothing().when(bookDao).update(book1);
        bookService.update(book1);
        verify(bookDao, times(ONE)).update(any());
    }

    @DisplayName("Get book")
    @Test
    public void whenGetBookThenItReturns() {
        Book book1 = new Book(ONE, BOOK_TITLE_KARENINA, new Author(""), new Genre(""));
        when(bookDao.getById(ONE)).thenReturn(book1);
        Book book = bookService.get(ONE);
        assertThat(book.getTitle()).isEqualTo(BOOK_TITLE_KARENINA);
    }

    @DisplayName("Get books")
    @Test
    public void whenGetAllBooksThenAllReturns() {
        Book book1 = new Book(ONE, BOOK_TITLE_KARENINA, new Author(""), new Genre(""));
        Book book2 = new Book(TWO, BOOK_TITLE_WAR_AND_PEACE, new Author(""), new Genre(""));
        when(bookDao.getAll()).thenReturn(List.of(book1, book2));

        List<Book> books = bookDao.getAll();

        Assertions.assertThat(books)
                .containsExactlyInAnyOrder(book1, book2);
    }

    @DisplayName("Delete book")
    @Test
    public void whenDeleteBookThenItDeleted() {
        doNothing().when(bookDao).deleteById(anyLong());
        bookService.delete(ONE);
        verify(bookDao, times(ONE)).deleteById(anyLong());
    }
}
