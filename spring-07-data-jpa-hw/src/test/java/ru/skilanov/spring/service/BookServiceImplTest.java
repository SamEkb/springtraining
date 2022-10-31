package ru.skilanov.spring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.skilanov.spring.repository.BookRepository;
import ru.skilanov.spring.model.Author;
import ru.skilanov.spring.model.Book;
import ru.skilanov.spring.model.Genre;
import ru.skilanov.spring.service.api.AuthorService;
import ru.skilanov.spring.service.api.GenreService;
import ru.skilanov.spring.service.impl.BookServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@DisplayName("Books service test")
@SpringBootTest(classes = {BookServiceImpl.class})
public class BookServiceImplTest {

    public static final String BOOK_TITLE_KARENINA = "Anna Karenina";
    public static final String BOOK_TITLE_WAR_AND_PEACE = "Voina I Mir";
    public static final int ONE = 1;
    public static final long TWO = 2;

    @MockBean
    private BookRepository bookRepository;
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
        when(bookRepository.save(book1)).thenReturn(book1);
        bookService.create(BOOK_TITLE_KARENINA, ONE, ONE);
        verify(bookRepository, times(ONE)).save(any());
    }

    @DisplayName("Update book")
    @Test
    public void whenUpdateBookThenItUpdated() {
        Book book1 = new Book(ONE, BOOK_TITLE_KARENINA, new Author(""), new Genre(""));
        when(bookRepository.save(book1)).thenReturn(book1);
        bookService.update(book1);
        verify(bookRepository, times(ONE)).save(any());
    }

    @DisplayName("Get book")
    @Test
    public void whenGetBookThenItReturns() {
        Book book1 = new Book(ONE, BOOK_TITLE_KARENINA, new Author(""), new Genre(""));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book1));
        Book book = bookService.get(ONE);
        assertThat(book.getTitle()).isEqualTo(BOOK_TITLE_KARENINA);
    }

    @DisplayName("Get books")
    @Test
    public void whenGetAllBooksThenAllReturns() {
        Book book1 = new Book(ONE, BOOK_TITLE_KARENINA, new Author(""), new Genre(""));
        Book book2 = new Book(TWO, BOOK_TITLE_WAR_AND_PEACE, new Author(""), new Genre(""));
        when(bookRepository.findAll()).thenReturn(List.of(book1, book2));

        List<Book> books = bookRepository.findAll();

        Assertions.assertThat(books)
                .containsExactlyInAnyOrder(book1, book2);
    }

    @DisplayName("Delete book")
    @Test
    public void whenDeleteBookThenItDeleted() {
        Book book1 = new Book(ONE, BOOK_TITLE_KARENINA, new Author(""), new Genre(""));
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book1));
        Book book = bookService.get(ONE);
        doNothing().when(bookRepository).delete(book);
        bookService.delete(ONE);
        verify(bookRepository, times(ONE)).delete(book);
    }
}
