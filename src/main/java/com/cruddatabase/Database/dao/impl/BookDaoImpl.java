package com.cruddatabase.Database.dao.impl;

import com.cruddatabase.Database.dao.BookDao;
import com.cruddatabase.Database.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class BookDaoImpl implements BookDao {
    private JdbcTemplate jdbcTemplate;

    public BookDaoImpl(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Book book) {
        jdbcTemplate.update(
                "INSERT INTO books (isbn, title, author_id) VALUES (?,?,?)",
                book.getIsBn(),book.getTitle(),book.getAuthorId()
        );
    }

    @Override
    public Optional<Book> findOne(String isBn) {
        List<Book> result = jdbcTemplate.query(
                "SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1",
                new BookRowMatcher(), isBn
        );
        return result.stream().findFirst();
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query(
                "SELECT isbn, title, author_id FROM books",
                new BookRowMatcher()
        );
    }

    @Override
    public void update(String isBn, Book book) {
        jdbcTemplate.update(
                "UPDATE books SET isbn = ?, title = ?, author_id = ? WHERE isbn = ?",
                book.getIsBn(),book.getTitle(),book.getAuthorId(),isBn
        );

    }

    @Override
    public void delete(String isBn) {
        jdbcTemplate.update(
                "DELETE FROM books WHERE isbn = ?",
                isBn
        );
    }

    public static class BookRowMatcher implements RowMapper<Book>{

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Book.builder()
                    .isBn(rs.getString("isbn"))
                    .title(rs.getString("title"))
                    .authorId(rs.getLong("author_id"))
                    .build();
        }
    }
}
