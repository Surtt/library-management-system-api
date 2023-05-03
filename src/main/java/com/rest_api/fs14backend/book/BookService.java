package com.rest_api.fs14backend.book;

import com.rest_api.fs14backend.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {

  @Autowired
  private BookRepository bookRepository;

  public List<Book> findAll() {
    return bookRepository.findAll();
  }

  public Optional<Book> findById(UUID id) {
    Optional<Book> bookOptional = bookRepository.findById(id);
    if (bookOptional.isEmpty()) {
      throw new NotFoundException("Book not found");
    }
    return bookRepository.findById(id);
  }

  public void deleteById(UUID id) {
    Optional<Book> bookOptional = bookRepository.findById(id);
    if (bookOptional.isEmpty()) {
      throw new NotFoundException("Book not found");
    }
    bookRepository.deleteById(id);
  }

  public Book createOne(Book book) {
    return bookRepository.save(book);
  }

  public Book updateOne(Book newBook, UUID id) {
    Optional<Book> bookOptional = bookRepository.findById(id);
    if (bookOptional.isEmpty()) {
      throw new NotFoundException("Book not found");
    }

    return bookRepository.findById(id).map(book -> {
      book.setIsbn(newBook.getIsbn());
      book.setTitle(newBook.getTitle());
      book.setDescription(newBook.getDescription());
      book.setPublisher(newBook.getPublisher());
      book.setStatus(newBook.getStatus());
      book.setPublishedDate(newBook.getPublishedDate());
      book.setQuantity(newBook.getQuantity());
      return bookRepository.save(book);
    }).orElseGet(() -> bookRepository.save(newBook));
  }
}
