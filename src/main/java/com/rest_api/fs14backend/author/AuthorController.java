package com.rest_api.fs14backend.author;

import com.rest_api.fs14backend.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/authors")
public class AuthorController {
  private final AuthorService authorService;

  @GetMapping
  public List<Author> findAll() {
    return authorService.findAll();
  }

  @GetMapping("/{id}")
  public Author findById(@PathVariable UUID id) {
    Author author = authorService.findById(id);
    if (author == null) {
      throw new NotFoundException("Author with id " + id + " not found");
    }
    return author;
  }

  @PostMapping
  public Author createOne(@RequestBody Author author) {
    return authorService.createOne(author);
  }

  @PutMapping("/{id}")
  public Author updateOne(@RequestBody Author newAuthor, @PathVariable UUID id) {
    Author author = authorService.findById(id);
    if (author == null) {
      throw new NotFoundException("Author with id " + id + " not found");
    }
    return authorService.updateOne(newAuthor, id);
  }

  @DeleteMapping("/{id}")
  public void deleteOne(@PathVariable UUID id) throws Exception {
    Author author = authorService.findById(id);
    if (author == null) {
      throw new NotFoundException("Author with id " + id + " not found");
    }
    authorService.deleteById(id);
  }
}
