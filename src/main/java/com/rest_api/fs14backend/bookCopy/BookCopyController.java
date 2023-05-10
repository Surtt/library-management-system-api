package com.rest_api.fs14backend.bookCopy;

import com.rest_api.fs14backend.book.BookService;
import com.rest_api.fs14backend.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/books/book-copy")
public class BookCopyController {
  private final BookCopyService bookCopyService;
  private final BookService bookService;
  private final BookCopyMapper bookCopyMapper;

  @PostMapping
  public BookCopy addOneBookCopy(@RequestBody BookCopyDTO bookCopyDTO) {
    return bookCopyService.addOneBookCopy(bookCopyDTO);
  }

  @DeleteMapping("/{bookCopyId}")
  public void removeOneBookCopy(@PathVariable UUID bookCopyId) {
    BookCopy bookCopy = bookCopyService.findById(bookCopyId);
    if (bookCopy == null) {
      throw new NotFoundException("Book Copy with id " + bookCopyId + " not found");
    }
    bookCopyService.deleteOneBookCopyById(bookCopyId);
  }
}
