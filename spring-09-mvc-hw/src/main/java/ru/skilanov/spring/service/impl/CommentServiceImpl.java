package ru.skilanov.spring.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skilanov.spring.dto.BookDto;
import ru.skilanov.spring.dto.CommentDto;
import ru.skilanov.spring.mapper.CommentMapper;
import ru.skilanov.spring.repository.CommentRepository;
import ru.skilanov.spring.exception.ObjectDoesNotExistException;
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

    @Override
    public void create(String name, long bookId) {
        BookDto commentedBook = bookService.get(bookId);
        var dto = new CommentDto(name, commentedBook);
        commentRepository.save(CommentMapper.INSTANCE.dtoToEntity(dto));
    }

    @Override
    public CommentDto get(long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(ObjectDoesNotExistException::new);
        return CommentMapper.INSTANCE.entityToDto(comment);
    }

    @Override
    public void delete(long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(ObjectDoesNotExistException::new);
        commentRepository.delete(comment);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CommentDto> getByBookId(long id) {
        return bookService.get(id).getComments();
    }
}
