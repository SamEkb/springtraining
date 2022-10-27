package ru.skilanov.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.skilanov.spring.dao.api.BookDao;
import ru.skilanov.spring.dao.api.CommentDao;
import ru.skilanov.spring.dao.impl.BookDaoImpl;
import ru.skilanov.spring.dao.impl.CommentDaoImpl;
import ru.skilanov.spring.model.Comment;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Comments dao test")
@DataJpaTest
@Import({CommentDaoImpl.class, BookDaoImpl.class})
public class CommentDaoImplTest {

    public static final String EXPECTED_DESCRIPTION_ONE = "cool";
    public static final String EXPECTED_DESCRIPTION_TWO = "horrible";
    public static final long EXPECTED_ID = 3L;
    public static final long DEFAULT_ID_ONE = 1L;
    public static final long DEFAULT_ID_TWO = 2L;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private TestEntityManager testEntityManager;

    @DisplayName("Add comment to db")
    @Test
    void shouldInsertComment() {
        var book = bookDao.getById(DEFAULT_ID_ONE);
        var expectedComment = new Comment(EXPECTED_DESCRIPTION_ONE, book.orElse(null));
        commentDao.save(expectedComment);
        Optional<Comment> actualComment = commentDao.getById(EXPECTED_ID);
        actualComment.ifPresent(it -> assertThat(it.getDescription()).isEqualTo(expectedComment.getDescription()));
    }

    @DisplayName("Returns expected comment")
    @Test
    void shouldReturnExpectedCommentById() {
        var book = bookDao.getById(DEFAULT_ID_ONE);
        var expectedComment = new Comment(DEFAULT_ID_ONE, EXPECTED_DESCRIPTION_ONE, book.orElse(null));
        Optional<Comment> actualComment = commentDao.getById(expectedComment.getId());
        actualComment.ifPresent(it -> assertThat(it).usingRecursiveComparison().isEqualTo(expectedComment));
    }

    @DisplayName("Deletes expected comment by id")
    @Test
    void shouldCorrectDeleteCommentById() {
        Comment commentForDeleting = testEntityManager.find(Comment.class, DEFAULT_ID_ONE);
        commentDao.delete(commentForDeleting);

        Comment deletedComment = testEntityManager.find(Comment.class, DEFAULT_ID_ONE);

        assertThat(deletedComment).isNull();
    }
}
