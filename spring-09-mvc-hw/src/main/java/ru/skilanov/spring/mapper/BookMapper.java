package ru.skilanov.spring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skilanov.spring.dto.BookDto;
import ru.skilanov.spring.model.Book;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDto entityToDto(Book book);

    Book dtoToEntity(BookDto dto);
}
