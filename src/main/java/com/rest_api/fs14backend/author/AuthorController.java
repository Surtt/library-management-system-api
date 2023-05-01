package com.rest_api.fs14backend.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/")
    public List<Author> findAll() {
        return authorService.findAll();
    }

    @GetMapping("/{lastname}")
    public ResponseEntity<Author> getOne(@PathVariable String lastname) {
        Author author = authorService.findOneByLastName(lastname);
        if (author != null) {
            return ResponseEntity.ok().body(author);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public Author createOne(@RequestBody Author author){
        return authorService.createOne(author);
    }

    @DeleteMapping("/{lastname}") //DEV reminder -> if lastname is in many authors all authors with that last name will be deleted
    public void delete(@PathVariable String lastname) {
        authorService.deleteByName(lastname);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        Author updatedAuthor = authorService.updateAuthor(id, author);
        return ResponseEntity.ok(updatedAuthor);
    }

}
