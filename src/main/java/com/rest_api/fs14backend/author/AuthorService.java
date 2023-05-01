package com.rest_api.fs14backend.author;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> findAll(){
        return authorRepository.findAll();
    }

    public Author findOneByLastName(String lastname){
        return authorRepository.findOneByLastname(lastname);
    }

    public Author createOne(Author author){
        return authorRepository.save(author);
    }

    @Transactional
    public void deleteByName(String lastname) {
        System.out.println("delete by name is called" + lastname);
        authorRepository.deleteBylastname(lastname);
    }

    @Transactional
    public Author updateAuthor(Long id, Author author) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id " + id));

        existingAuthor.setAuthorname(author.getAuthorname());
        existingAuthor.setLastname(author.getLastname());
        existingAuthor.setDateofbirth(author.getDateofbirth());

        return authorRepository.save(existingAuthor);
    }



}
