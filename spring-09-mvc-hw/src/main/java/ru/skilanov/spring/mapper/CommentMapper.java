package ru.skilanov.spring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skilanov.spring.dto.CommentDto;
import ru.skilanov.spring.model.Comment;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    CommentDto entityToDto(Comment comment);

    Comment dtoToEntity(CommentDto dto);
}
