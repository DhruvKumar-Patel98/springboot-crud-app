package com.cruddatabase.Database.dao.impl;

import com.cruddatabase.Database.TestDataUtils;
import com.cruddatabase.Database.domain.Author;
import com.cruddatabase.Database.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookDaoImplIntegrationTests {

    private AuthorDaoImpl authorDao;
    private BookDaoImpl underTest;

    @Autowired
    public BookDaoImplIntegrationTests(BookDaoImpl underTest, AuthorDaoImpl authorDao){
        this.underTest = underTest;
        this.authorDao = authorDao;
    }

    @Test
    public void testThatCreateBookAndRecalled(){
        Author author = TestDataUtils.createTestAuthorA();
        authorDao.create(author);
        Book book = TestDataUtils.createTestBookA();
        underTest.create(book);
        Optional<Book> result = underTest.findOne(book.getIsBn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

}
