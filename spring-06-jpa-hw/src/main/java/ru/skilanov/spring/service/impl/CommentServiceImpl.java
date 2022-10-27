package ru.skilanov.spring.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skilanov.spring.dao.api.CommentDao;
import ru.skilanov.spring.exception.ObjectDoesNotExistException;
import ru.skilanov.spring.model.Book;
import ru.skilanov.spring.model.Comment;
import ru.skilanov.spring.service.api.BookService;
import ru.skilanov.spring.service.api.CommentService;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentDao commentDao;
    private final BookService bookService;

    public CommentServiceImpl(CommentDao commentDao, BookService bookService) {
        this.commentDao = commentDao;
        this.bookService = bookService;
    }

    @Transactional
    @Override
    public void create(String name, long bookId) {
        Book commentedBook = bookService.get(bookId);
        var comment = new Comment(name, commentedBook);
        commentDao.save(comment);
    }

    @Override
    public Comment get(long id) {
        return commentDao.getById(id).orElseThrow(ObjectDoesNotExistException::new);
    }

    @Transactional
    @Override
    public void delete(long id) {
        Comment comment = commentDao.getById(id).orElseThrow(ObjectDoesNotExistException::new);
        commentDao.delete(comment);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> getByBookId(long id) {
        return bookService.get(id).getComments();
    }
}
