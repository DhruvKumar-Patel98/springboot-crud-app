package com.cruddatabase.Database.controller;

import com.cruddatabase.Database.dao.AuthorDao;
import com.cruddatabase.Database.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorDao authorDao;

    @PostMapping
    public String createAuthor(@RequestBody Author author){
        authorDao.create(author);
        return "Author Create";
    }

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorDao.FindAll();
    }

    @GetMapping("/{id}")
    public Optional<Author> getAuthorById(@PathVariable Long id) {
        return authorDao.findOne(id);
    }

    @PutMapping("/{id}")
    public String updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        authorDao.update(id, author);
        return "Author updated successfully!";
    }

    @DeleteMapping("/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorDao.delete(id);
        return "Author deleted successfully!";
    }
}
