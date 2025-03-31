package com.cruddatabase.Database.dao;

import com.cruddatabase.Database.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    void create(Author author);

    Optional<Author> findOne(long l);

    void update(Long id, Author author);

    List<Author> FindAll();

    void delete(Long id);
}
