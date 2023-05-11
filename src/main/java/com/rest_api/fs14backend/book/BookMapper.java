package com.rest_api.fs14backend.book;

import com.rest_api.fs14backend.category.Category;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
  public Book toBook(BookDTO bookDTO, Category category) {
    return new Book(bookDTO.getIsbn(), bookDTO.getTitle(), bookDTO.getDescription(), bookDTO.getImage(),
            bookDTO.getPublisher(), bookDTO.getPublishedDate(), category, bookDTO.getAuthorId());
  }
}
