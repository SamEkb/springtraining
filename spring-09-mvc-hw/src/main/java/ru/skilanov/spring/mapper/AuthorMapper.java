package ru.skilanov.spring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skilanov.spring.dto.AuthorDto;
import ru.skilanov.spring.model.Author;

@Mapper
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDto entityToDto(Author author);

    Author dtoToEntity(AuthorDto dto);
}
