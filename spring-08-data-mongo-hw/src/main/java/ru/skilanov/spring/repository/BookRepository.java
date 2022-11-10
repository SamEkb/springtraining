package ru.skilanov.spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.skilanov.spring.model.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

}
