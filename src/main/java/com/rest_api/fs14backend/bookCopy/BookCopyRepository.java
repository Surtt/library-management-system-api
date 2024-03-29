package com.rest_api.fs14backend.bookCopy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookCopyRepository extends JpaRepository<BookCopy, UUID> {
  List<BookCopy> findAllByBookId(UUID bookId);
}
