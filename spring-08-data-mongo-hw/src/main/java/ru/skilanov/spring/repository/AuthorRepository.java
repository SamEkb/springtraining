package ru.skilanov.spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.skilanov.spring.model.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {
}
