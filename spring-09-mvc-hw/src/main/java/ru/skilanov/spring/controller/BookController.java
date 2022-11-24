package ru.skilanov.spring.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.skilanov.spring.dto.AuthorDto;
import ru.skilanov.spring.dto.BookDto;
import ru.skilanov.spring.dto.GenreDto;
import ru.skilanov.spring.model.Author;
import ru.skilanov.spring.model.Book;
import ru.skilanov.spring.model.Genre;
import ru.skilanov.spring.service.api.AuthorService;
import ru.skilanov.spring.service.api.BookService;
import ru.skilanov.spring.service.api.GenreService;

import java.util.List;


@Controller
public class BookController {
    private final BookService bookService;

    private final AuthorService authorService;

    private final GenreService genreService;

    public BookController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping("/")
    public String listPage(Model model) {
        List<BookDto> books = bookService.getAll();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        List<AuthorDto> authors = authorService.getAll();
        List<GenreDto> genres = genreService.getAll();
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        model.addAttribute("book", new BookDto());
        return "create";
    }

    @PostMapping(value = "/create")
    public String createBook(BookDto dto) {
        bookService.create(dto);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        BookDto book = bookService.get(id);
        List<AuthorDto> authors = authorService.getAll();
        List<GenreDto> genres = genreService.getAll();
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        model.addAttribute("book", book);
        return "edit";
    }

    @PostMapping(value ="/edit")
    public String saveBook(BookDto dto) {
        bookService.update(dto);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteBook(@RequestParam("id") long id) {
        bookService.delete(id);
        return "redirect:/";
    }
}
