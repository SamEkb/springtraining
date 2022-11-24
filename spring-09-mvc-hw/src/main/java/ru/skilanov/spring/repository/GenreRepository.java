package ru.skilanov.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skilanov.spring.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
