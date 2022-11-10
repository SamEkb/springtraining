package ru.skilanov.spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.skilanov.spring.model.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {

}
