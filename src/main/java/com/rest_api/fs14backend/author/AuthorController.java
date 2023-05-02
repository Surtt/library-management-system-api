package com.rest_api.fs14backend.author;

import com.rest_api.fs14backend.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
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
    public Author findById(@PathVariable UUID id) {
        Author author = authorService.findById(id);
        if (author == null) {
            throw new NotFoundException("Author not found");
        }
        return author;
    }

    @PostMapping
    public Author createOne(@RequestBody Author author) {
        return authorService.createOne(author);
    }

    @PutMapping("/{id}")
    public Author updateOne(@RequestBody Author author, @PathVariable UUID id) {
        if (author == null) {
            throw new NotFoundException("Author not found");
        }
        return authorService.updateOne(author, id);
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable UUID id) {
        Author author = authorService.findById(id);
        if (author == null) {
            throw new NotFoundException("Book not found");
        }
        authorService.deleteById(id);
    }
}
