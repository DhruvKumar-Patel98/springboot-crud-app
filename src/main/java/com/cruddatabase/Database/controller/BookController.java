package com.cruddatabase.Database.controller;

import com.cruddatabase.Database.dao.BookDao;
import com.cruddatabase.Database.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookDao bookDao;

    @PostMapping
    public String createBook(@RequestBody Book book){
        bookDao.create(book);
        return "Book Created Successfully";
    }

    @GetMapping
    public List<Book> getAllBook(){
        return bookDao.findAll();
    }

    @GetMapping("/{isBn}")
    public Optional<Book> getBookById(@PathVariable String isBn){
        return bookDao.findOne(isBn);
    }

    @PutMapping("/{isBn}")
    public String updateBook(@PathVariable String isBn, Book book){
        bookDao.update(isBn, book);
        return "Book Update Successfully";
    }

    @DeleteMapping("/{isBn}")
    public String deleteBook(@PathVariable String isBn){
        bookDao.delete(isBn);
        return "Book Deleted Successfully";
    }

}
