package ru.skilanov.spring.service.api;

import ru.skilanov.spring.dto.CommentDto;
import ru.skilanov.spring.model.Comment;

import java.util.List;

public interface CommentService {
    void create(String name, long bookId);

    CommentDto get(long id);

    void delete(long id);

    List<CommentDto> getByBookId(long id);
}
