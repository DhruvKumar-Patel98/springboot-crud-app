package com.cruddatabase.Database;

import com.cruddatabase.Database.domain.Author;
import com.cruddatabase.Database.domain.Book;

public final class TestDataUtils {
    public static Author createTestAuthorA(){
        return Author.builder()
                .id(1L)
                .name("Dhruvkumar")
                .age(24)
                .build();
    }

    public static Author createTestAuthorB(){
        return Author.builder()
                .id(2L)
                .name("Jay Kumar Patel")
                .age(28)
                .build();
    }

    public static Author createTestAuthorC(){
        return Author.builder()
                .id(3L)
                .name("Niki Patel")
                .age(22)
                .build();
    }

    public static Book createTestBookA() {
        return Book.builder()
                .isBn("12-21-234")
                .title("Attack on titan")
                .authorId(1L)
                .build();
    }

    public static Book createTestBookB() {
        return Book.builder()
                .isBn("12-21-345")
                .title("Death Note")
                .authorId(2L)
                .build();
    }

    public static Book createTestBookC() {
        return Book.builder()
                .isBn("12-21-456")
                .title("Naruto")
                .authorId(3L)
                .build();
    }
}
