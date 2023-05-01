package com.rest_api.fs14backend.author;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findOneByLastname(String lastname);

    void deleteBylastname(String lastname);


}
