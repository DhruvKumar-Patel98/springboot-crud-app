package com.cruddatabase.Database.dao.impl;

import com.cruddatabase.Database.TestDataUtils;
import com.cruddatabase.Database.domain.Author;
import com.cruddatabase.Database.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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

    @Test
    public void testThatFinAllAndRecalled(){
        Author authorA = TestDataUtils.createTestAuthorA();
        authorDao.create(authorA);
        Author authorB = TestDataUtils.createTestAuthorB();
        authorDao.create(authorB);
        Author authorC = TestDataUtils.createTestAuthorC();
        authorDao.create(authorC);
        Book bookA = TestDataUtils.createTestBookA();
        Book bookB = TestDataUtils.createTestBookB();
        Book bookC = TestDataUtils.createTestBookC();
        underTest.create(bookA);
        underTest.create(bookB);
        underTest.create(bookC);
        List<Book> result = underTest.findAll();
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(3);
        assertThat(result).containsExactly(bookA,bookB,bookC);
    }

}
