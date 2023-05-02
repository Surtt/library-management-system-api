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

  @PutMapping("/{id}")
  public Book updateOne(@RequestBody Book book, @PathVariable UUID id) {
    if (book == null) {
      throw new NotFoundException("Book not found");
    }
    return bookService.updateOne(book, id);
  }

  @DeleteMapping("/{id}")
  public void deleteOne(@PathVariable UUID id) {
    Book book = bookService.findById(id);
    System.out.println(book);
    if (book == null) {
      throw new NotFoundException("Book not found");
    }
    bookService.deleteById(id);
  }
}
