package com.rest_api.fs14backend.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/books")
public class BookController {

  @Autowired
  private BookService bookService;

  @GetMapping
  public List<Book> findAll() {
    return bookService.findAll();
  }

  @GetMapping("/{id}")
  public Optional<Book> findById(@PathVariable UUID id) {
    return bookService.findById(id);
  }

  @PostMapping
  public Book createOne(@RequestBody Book book) {
    return bookService.createOne(book);
  }

  @PutMapping("/{id}")
  public Book updateOne(@RequestBody Book book, @PathVariable UUID id) {
    return bookService.updateOne(book, id);
  }

  @DeleteMapping("/{id}")
  public void deleteOne(@PathVariable UUID id) {
    bookService.deleteById(id);
  }
}
