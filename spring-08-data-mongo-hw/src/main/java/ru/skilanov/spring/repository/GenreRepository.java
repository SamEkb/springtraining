package ru.skilanov.spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.skilanov.spring.model.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {

}
