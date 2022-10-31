package ru.skilanov.spring.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skilanov.spring.repository.CommentRepository;
import ru.skilanov.spring.exception.ObjectDoesNotExistException;
import ru.skilanov.spring.model.Book;
import ru.skilanov.spring.model.Comment;
import ru.skilanov.spring.service.api.BookService;
import ru.skilanov.spring.service.api.CommentService;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BookService bookService;

    public CommentServiceImpl(CommentRepository commentRepository, BookService bookService) {
        this.commentRepository = commentRepository;
        this.bookService = bookService;
    }

    @Transactional
    @Override
    public void create(String name, long bookId) {
        Book commentedBook = bookService.get(bookId);
        var comment = new Comment(name, commentedBook);
        commentRepository.save(comment);
    }

    @Override
    public Comment get(long id) {
        return commentRepository.findById(id).orElseThrow(ObjectDoesNotExistException::new);
    }

    @Transactional
    @Override
    public void delete(long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(ObjectDoesNotExistException::new);
        commentRepository.delete(comment);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> getByBookId(long id) {
        return bookService.get(id).getComments();
    }
}
