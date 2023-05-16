package com.rest_api.fs14backend.author;

import com.rest_api.fs14backend.book.Book;
import com.rest_api.fs14backend.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
    return authorRepository.findById(id)
            .orElse(null);
  }

  public void deleteById(UUID id) throws Exception {
    Book book = bookRepository.findAll()
            .stream()
            .filter(b -> b.getAuthors()
                    .stream()
                    .filter(author -> Objects.equals(author.getId(), id))
                    .isParallel())
            .findFirst()
            .orElse(null);

    if (book != null) {
      throw new Exception("Book is existed");
    } else {
      authorRepository.deleteById(id);
    }
  }

  public Author createOne(Author author) {
    return authorRepository.save(author);
  }

  public Author updateOne(Author newAuthor, UUID id) {
    return authorRepository.findById(id)
            .map(author -> {
              author.setName(newAuthor.getName());
              return authorRepository.save(author);
            })
            .orElseGet(() -> authorRepository.save(newAuthor));
  }
}
