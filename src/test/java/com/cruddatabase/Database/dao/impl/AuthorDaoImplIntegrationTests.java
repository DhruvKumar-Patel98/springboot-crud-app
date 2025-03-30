package com.cruddatabase.Database.dao.impl;

import com.cruddatabase.Database.TestDataUtils;
import com.cruddatabase.Database.domain.Author;
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
public class AuthorDaoImplIntegrationTests {

    private AuthorDaoImpl underTest;

    @Autowired
    public AuthorDaoImplIntegrationTests(AuthorDaoImpl underTest){
        this.underTest = underTest;
    }

    @Test
    public void testThatCreateAuthorAndRecalled(){
        Author author = TestDataUtils.createTestAuthorA();
        underTest.create(author);
        Optional<Author> result = underTest.findOne(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);

    }

    @Test
    public void testThatFindAllAndRecalled(){
        Author authorA = TestDataUtils.createTestAuthorA();
        Author authorB = TestDataUtils.createTestAuthorB();
        Author authorC = TestDataUtils.createTestAuthorC();
        underTest.create(authorA);
        underTest.create(authorB);
        underTest.create(authorC);
        List<Author> result = underTest.FindAll();
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(3);
        assertThat(result).containsExactly(authorA,authorB,authorC);
    }

}
