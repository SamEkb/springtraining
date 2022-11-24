package ru.skilanov.spring.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skilanov.spring.model.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(value = "bookPropertiesEntityGraph")
    @Override
    List<Book> findAll();
}
