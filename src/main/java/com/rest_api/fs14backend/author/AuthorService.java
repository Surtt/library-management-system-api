package com.rest_api.fs14backend.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorService {
  private final AuthorRepository authorRepository;

  public List<Author> findAll() {
    return authorRepository.findAll();
  }

  public Author findById(UUID id) {
    return authorRepository.findById(id)
            .orElse(null);
  }

  public void deleteById(UUID id) {
    authorRepository.deleteById(id);
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
