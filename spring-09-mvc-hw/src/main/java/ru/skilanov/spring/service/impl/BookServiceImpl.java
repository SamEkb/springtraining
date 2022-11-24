package ru.skilanov.spring.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skilanov.spring.dto.BookDto;
import ru.skilanov.spring.exception.ObjectDoesNotExistException;
import ru.skilanov.spring.mapper.BookMapper;
import ru.skilanov.spring.model.Author;
import ru.skilanov.spring.model.Book;
import ru.skilanov.spring.model.Genre;
import ru.skilanov.spring.repository.AuthorRepository;
import ru.skilanov.spring.repository.BookRepository;
import ru.skilanov.spring.repository.GenreRepository;
import ru.skilanov.spring.service.api.BookService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreService;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreService) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreService = genreService;
    }

    @Transactional
    @Override
    public void create(BookDto dto) {
        Author author = authorRepository.findById(dto.getAuthor().getId()).orElseThrow(ObjectDoesNotExistException::new);
        Genre genre = genreService.findById(dto.getGenre().getId()).orElseThrow(ObjectDoesNotExistException::new);
        var book = new Book(dto.getTitle(), author, genre);
        bookRepository.save(book);
    }

    @Override
    public void update(BookDto dto) {
        Book book = BookMapper.INSTANCE.dtoToEntity(dto);
        bookRepository.save(book);
    }

    @Override
    public BookDto get(long id) {
        return bookRepository.findById(id)
                .map(BookMapper.INSTANCE::entityToDto)
                .orElseThrow(ObjectDoesNotExistException::new);
    }

    @Override
    public List<BookDto> getAll() {
        return bookRepository.findAll().stream()
                .map(BookMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(ObjectDoesNotExistException::new);
        bookRepository.delete(book);
    }
}
