package com.rest_api.fs14backend.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

  @Autowired
  private BookRepository bookRepository;

  public List<Book> findAll() {
    return bookRepository.findAll();
  }

  public Book findById(UUID id) {
    return bookRepository.findById(id).orElse(null);
  }

  public void deleteById(UUID id) {
    bookRepository.deleteById(id);
  }

  public Book createOne(Book book) {
    return bookRepository.save(book);
  }
}
