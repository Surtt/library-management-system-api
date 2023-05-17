package com.rest_api.fs14backend.author;

import com.rest_api.fs14backend.book.Book;
import com.rest_api.fs14backend.book.BookRepository;
import com.rest_api.fs14backend.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorService {
  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;

  public List<Author> findAll() {
    return authorRepository.findAll();
  }

  public Author findById(UUID id) {
    return authorRepository.findById(id).orElse(null);
  }

  public void deleteById(UUID id) throws Exception {
    Author author = authorRepository.findById(id).get();
    Set<Book> authorBooks = author.getBooks();
    
    if (!authorBooks.isEmpty()) {
      throw new NotFoundException("It is not possible to delete the author because it is associated with a book");
    } else {
      authorRepository.deleteById(id);
    }
  }

  public Author createOne(Author author) {
    return authorRepository.save(author);
  }

  public Author updateOne(Author newAuthor, UUID id) {
    return authorRepository.findById(id).map(author -> {
      author.setName(newAuthor.getName());
      return authorRepository.save(author);
    }).orElseGet(() -> authorRepository.save(newAuthor));
  }
}
