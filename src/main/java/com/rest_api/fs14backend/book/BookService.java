package com.rest_api.fs14backend.book;

import com.rest_api.fs14backend.author.Author;
import com.rest_api.fs14backend.author.AuthorRepository;
import com.rest_api.fs14backend.category.Category;
import com.rest_api.fs14backend.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {
  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;
  private final CategoryRepository categoryRepository;
  private final BookMapper bookMapper;

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

  public Book updateOne(Book newBook, UUID id) {
    UUID categoryId = newBook.getCategory();
    Category category = categoryRepository.findById(categoryId).get();

    return bookRepository.findById(id).map(book -> {
      book.setIsbn(newBook.getIsbn());
      book.setTitle(newBook.getTitle());
      book.setDescription(newBook.getDescription());
      book.setImage(newBook.getImage());
      book.setPublisher(newBook.getPublisher());
      book.setPublishedDate(newBook.getPublishedDate());
      book.setCategory(category);
      return bookRepository.save(book);
    }).orElseGet(() -> bookRepository.save(newBook));
  }

  public Book assignAuthorToBook(UUID bookId, UUID authorId) {
    Set<Author> authorSet = null;
    Book book = bookRepository.findById(bookId).get();
    Author author = authorRepository.findById(authorId).get();
    authorSet = book.getAuthors();
    authorSet.add(author);
    book.setAuthors(authorSet);
    return bookRepository.save(book);
  }
}
