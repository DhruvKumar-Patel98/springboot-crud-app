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
}
