package ru.skilanov.spring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skilanov.spring.dto.GenreDto;
import ru.skilanov.spring.model.Genre;

@Mapper
public interface GenreMapper {

    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    GenreDto entityToDto(Genre genre);

    Genre dtoToEntity(GenreDto dto);
}
