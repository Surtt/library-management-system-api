package com.rest_api.fs14backend.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> findAll(){
        return authorRepository.findAll();
    }

    public Author findById(UUID id){
        return authorRepository.findById(id).orElse(null);
    }

    public void deleteById(UUID id) {
        authorRepository.deleteById(id);
    }

    public Author createOne(Author author){
        return authorRepository.save(author);
    }

    public Author updateOne(Author author, UUID id) {
        author.setId(id);

        return authorRepository.save(author);
    }
}
