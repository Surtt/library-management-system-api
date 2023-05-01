package com.rest_api.fs14backend.book;

import com.rest_api.fs14backend.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
  public Book findById(@PathVariable UUID id) {
    Book book = bookService.findById(id);

    if (book == null) {
      throw new NotFoundException("Book not found");
    }
    return book;
  }

  @PostMapping
  public Book createOne(@RequestBody Book book) {
    return bookService.createOne(book);
  }

  @DeleteMapping("/{id}")
  public void deleteOne(@PathVariable UUID id) {
    bookService.deleteById(id);
  }
}
