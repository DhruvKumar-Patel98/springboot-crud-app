package com.cruddatabase.Database.dao.impl;

import com.cruddatabase.Database.TestDataUtils;
import com.cruddatabase.Database.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl underTest;

    @Test
    public void testThatCreateBookAndGeneratesCorrectSQL(){
        Book book = TestDataUtils.createTestBookA();
        underTest.create(book);

        verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn, title, author_id) VALUES (?,?,?)"),
                eq("12-21-234"),eq("Attack on titan"), eq(1L)
        );
    }

    @Test
    public void testThatFindOneBookAndGenerateCorrectSQL(){
        underTest.findOne("12-21-234");

        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1"),
                ArgumentMatchers.<BookDaoImpl.BookRowMatcher>any(),
                eq("12-21-234")
        );
    }

    @Test
    public void testThatFindAllBookAndGenerateCorrectSQL(){
        underTest.findAll();
        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books"),
                ArgumentMatchers.<BookDaoImpl.BookRowMatcher>any()
        );
    }

    @Test
    public void testThatUpdateBookAndGenerateCorrectSQL(){
        Book bookA = TestDataUtils.createTestBookA();
        String isBn = "12-21-234";
        underTest.update(isBn, bookA);
        verify(jdbcTemplate).update(
                "UPDATE books SET isbn = ?, title = ?, author_id = ? WHERE isbn = ?",
                "12-21-234","Attack on titan", 1L, isBn
        );
    }

    @Test
    public void testThatDeleteBookAndGenerateCorrectSQL(){
        String isBn = "12-21-234";
        underTest.delete(isBn);
        verify(jdbcTemplate).update(
                "DELETE FROM books WHERE isbn = ?",
                isBn
        );
    }
}
