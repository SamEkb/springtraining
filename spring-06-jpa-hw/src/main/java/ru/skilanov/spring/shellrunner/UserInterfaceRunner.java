package ru.skilanov.spring.shellrunner;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.skilanov.spring.model.Author;
import ru.skilanov.spring.model.Book;
import ru.skilanov.spring.model.Comment;
import ru.skilanov.spring.model.Genre;
import ru.skilanov.spring.service.api.AuthorService;
import ru.skilanov.spring.service.api.BookService;
import ru.skilanov.spring.service.api.CommentService;
import ru.skilanov.spring.service.api.GenreService;

import java.util.List;

@ShellComponent
public class UserInterfaceRunner {
    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookService bookService;
    private final CommentService commentService;

    public UserInterfaceRunner(AuthorService authorService, GenreService genreService, BookService bookService,
                               CommentService commentService) {
        this.authorService = authorService;
        this.genreService = genreService;
        this.bookService = bookService;
        this.commentService = commentService;
    }

    @ShellMethod(value = "Create book", key = {"create_book", "cb"})
    public void createBook(String title, long authorId, long genreId) {
        bookService.create(title, authorId, genreId);
    }

    @ShellMethod(value = "Get book", key = {"get_book", "gb"})
    public Book getBook(long id) {
        return bookService.get(id);
    }

    @ShellMethod(value = "Show all books", key = {"books", "bs"})
    public List<Book> showAllBooks() {
        return bookService.getAll();
    }

    @ShellMethod(value = "Delete book", key = {"delete_book", "db"})
    public void deleteBook(long id) {
        bookService.delete(id);
    }

    @ShellMethod(value = "Create author", key = {"create_author", "ca"})
    public void createAuthor(String name) {
        authorService.create(name);
    }

    @ShellMethod(value = "Get author", key = {"get_author", "ga"})
    public Author getAuthor(long id) {
        return authorService.get(id);
    }

    @ShellMethod(value = "Show all authors", key = {"authors", "as"})
    public List<Author> showAllAuthors() {
        return authorService.getAll();
    }

    @ShellMethod(value = "Delete book", key = {"delete_author", "da"})
    public void deleteAuthor(long id) {
        authorService.delete(id);
    }

    @ShellMethod(value = "Create genre", key = {"create_genre", "cg"})
    public void createGenre(String name) {
        genreService.create(name);
    }

    @ShellMethod(value = "Get genre", key = {"get_genre", "gg"})
    public Genre getGenre(long id) {
        return genreService.get(id);
    }

    @ShellMethod(value = "Show all genres", key = {"genres", "gs"})
    public List<Genre> showAllGenres() {
        return genreService.getAll();
    }

    @ShellMethod(value = "Delete genre", key = {"delete_genre", "dg"})
    public void deleteGenre(long id) {
        genreService.delete(id);
    }

    @ShellMethod(value = "Create comment", key = {"create_comment", "cc"})
    public void createComment(String description, long bookId) {
        commentService.create(description, bookId);
    }

    @ShellMethod(value = "Get comments", key = {"get_comment_by_book_id", "gcbbi"})
    public List<Comment> getCommentsByBookId(long bookId) {
        return commentService.getByBookId(bookId);
    }

    @ShellMethod(value = "Get comment", key = {"get_comment", "gc"})
    public Comment getComment(long id) {
        return commentService.get(id);
    }

    @ShellMethod(value = "Delete comment", key = {"delete_comment", "dc"})
    public void deleteComment(long id) {
        commentService.delete(id);
    }
}
