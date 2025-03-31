package com.cruddatabase.Database.dao;

import com.cruddatabase.Database.domain.Author;
import com.cruddatabase.Database.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void create(Book book);

    Optional<Book> findOne(String s);

    List<Book> findAll();

    void update(String isBn, Book book);
}
