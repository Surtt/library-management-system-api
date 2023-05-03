package com.rest_api.fs14backend.author;

import com.rest_api.fs14backend.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {
  @Autowired
  private AuthorRepository authorRepository;

  public List<Author> findAll() {
    return authorRepository.findAll();
  }

  public Optional<Author> findById(UUID id) {
    Optional<Author> authorOptional = authorRepository.findById(id);
    if (authorOptional.isEmpty()) {
      throw new NotFoundException("Author not found");
    }
    return authorRepository.findById(id);
  }

  public void deleteById(UUID id) {
    Optional<Author> authorOptional = authorRepository.findById(id);
    if (authorOptional.isEmpty()) {
      throw new NotFoundException("Author not found");
    }
    authorRepository.deleteById(id);
  }

  public Author createOne(Author author) {
    return authorRepository.save(author);
  }

  public Author updateOne(Author newAuthor, UUID id) {
    Optional<Author> authorOptional = authorRepository.findById(id);
    if (authorOptional.isEmpty()) {
      throw new NotFoundException("Author not found");
    }

    return authorRepository.findById(id).map(author -> {
      author.setName(newAuthor.getName());
      return authorRepository.save(author);
    }).orElseGet(() -> authorRepository.save(newAuthor));
  }
}
