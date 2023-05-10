package com.rest_api.fs14backend.bookCopy;

import com.rest_api.fs14backend.book.Book;
import org.springframework.stereotype.Component;

@Component
public class BookCopyMapper {
  public BookCopy toBookCopy(BookCopyDTO bookCopyDTO, Book book) {
    return new BookCopy(book);
  }
}
