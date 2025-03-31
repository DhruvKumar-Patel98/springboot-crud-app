package com.cruddatabase.Database.dao.impl;

import com.cruddatabase.Database.TestDataUtils;
import com.cruddatabase.Database.domain.Author;
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
public class AuthorDaoImplTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDaoImpl underTest;

    @Test
    public void testThatCreateAuthorAndGeneratesCorrectSQL(){
        Author author = Author.builder()
                .id(1L)
                .name("Dhruvkumar")
                .age(24)
                .build();

        underTest.create(author);
        verify(jdbcTemplate).update(
                eq("INSERT INTO authors (id, name, age) VALUES (?,?,?)"),
                eq(1L),eq("Dhruvkumar"),eq(24)
        );
    }

    @Test
    public void testThatFindOneAuthorAndGenerateCorrectSQL(){

        underTest.findOne(1L);

        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                eq(1L)
        );
    }

    @Test
    public void testThatFindAllAuthorAndGeneratesCorrectSQL(){
       underTest.FindAll();

        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any()
        );
    }

    @Test
    public void testThatUpdateAuthorAndGenerateCorrectSQL(){
        Author author = TestDataUtils.createTestAuthorB();
        Long id = 1L;
        underTest.update(id, author);

        verify(jdbcTemplate).update(
                "UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?",
                2L, "Jay Kumar Patel", 28, id
        );
    }

    @Test
    public void testThatDeleteAuthorAndGenerateCorrectSQL(){
        Long id = 1L;
        underTest.delete(id);
        verify(jdbcTemplate).update(
                "DELETE FROM authors WHERE id = ?",
                id
        );
    }
}
