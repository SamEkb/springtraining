package ru.skilanov.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skilanov.spring.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
