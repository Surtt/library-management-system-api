package com.rest_api.fs14backend.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/authors")
public class AuthorController {
  @Autowired
  private AuthorService authorService;

  @GetMapping
  public List<Author> findAll() {
    return authorService.findAll();
  }

  @GetMapping("/{id}")
  public Optional<Author> findById(@PathVariable UUID id) {
    return authorService.findById(id);
  }

  @PostMapping
  public Author createOne(@RequestBody Author author) {
    return authorService.createOne(author);
  }

  @PutMapping("/{id}")
  public Author updateOne(@RequestBody Author author, @PathVariable UUID id) {
    return authorService.updateOne(author, id);
  }

  @DeleteMapping("/{id}")
  public void deleteOne(@PathVariable UUID id) {
    authorService.deleteById(id);
  }
}
