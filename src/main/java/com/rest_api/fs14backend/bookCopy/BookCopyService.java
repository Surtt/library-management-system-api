package com.rest_api.fs14backend.bookCopy;

import com.rest_api.fs14backend.book.Book;
import com.rest_api.fs14backend.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookCopyService {
  private final BookCopyRepository bookCopyRepository;
  private final BookRepository bookRepository;
  private final BookCopyMapper bookCopyMapper;

  public BookCopy findById(UUID id) {
    return bookCopyRepository.findById(id)
            .orElse(null);
  }

  public BookCopy addOneBookCopy(BookCopyDTO bookCopyDTO) {
    List<BookCopy> bookCopyList = null;
    UUID bookId = bookCopyDTO.getBookId();
    Book book = bookRepository.findById(bookId)
            .get();

    BookCopy bookCopy = bookCopyMapper.toBookCopy(bookCopyDTO, book);
    bookCopyList = book.getBookCopyList();
    bookCopyList.add(bookCopy);
    book.setBookCopyList(bookCopyList);
    book.increaseBookQuantity();
    return bookCopyRepository.save(bookCopy);
  }

  public void deleteOneBookCopyById(UUID bookCopyId) {
    List<BookCopy> bookCopyList = null;
    BookCopy bookCopy = bookCopyRepository.findById(bookCopyId)
            .get();
    UUID bookId = bookCopy.getBook()
            .getId();
    Book book = bookRepository.findById(bookId)
            .get();

    bookCopyList = book.getBookCopyList();
    bookCopyList.remove(bookCopy);
    book.setBookCopyList(bookCopyList);
    book.decreaseBookQuantity();
    bookCopyRepository.deleteById(bookCopyId);
  }
}
