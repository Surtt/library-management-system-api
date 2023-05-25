package com.rest_api.fs14backend.book;

import com.rest_api.fs14backend.author.Author;
import com.rest_api.fs14backend.author.AuthorService;
import com.rest_api.fs14backend.category.Category;
import com.rest_api.fs14backend.category.CategoryService;
import com.rest_api.fs14backend.exceptions.NotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/books")
public class BookController {
  private final BookService bookService;
  private final CategoryService categoryService;
  private final AuthorService authorService;
  private final BookMapper bookMapper;

  @CrossOrigin(origins = {"http://localhost:5173/", "https://library-management-system-chi.vercel.app/"})
  @GetMapping
  public List<Book> findAll() {
    return bookService.findAll();
  }

  @GetMapping("/{id}")
  public Book findById(@PathVariable UUID id) {
    Book book = bookService.findById(id);
    if (book == null) {
      throw new NotFoundException("Book with id " + id + " not found");
    }
    return book;
  }

  @PostMapping
  public Book createOne(@RequestBody @Valid BookDTO bookDTO) throws Exception {
    UUID categoryId = bookDTO.getCategoryId();
    UUID authorId = bookDTO.getAuthorId();

    Category category = categoryService.findById(categoryId);
    Author author = authorService.findById(authorId);

    Book book = bookMapper.toBook(bookDTO, category);
    Boolean isAuthorExist = Collections.singletonList(book.getAuthors()).contains(author);
    if (isAuthorExist) {
      throw new Exception("This author already exists");
    }
    book.addAuthor(author);
    return bookService.createOne(book);
  }

  @PutMapping("/{id}")
  public Book updateOne(@RequestBody @Valid BookDTO bookDTO, @PathVariable UUID id) {
    UUID categoryId = bookDTO.getCategoryId();

    Category category = categoryService.findById(categoryId);

    Book newBook = bookMapper.toBook(bookDTO, category);
    Book book = bookService.findById(id);

    if (book == null) {
      throw new NotFoundException("Book with id " + id + " not found");
    }

    return bookService.updateOne(newBook, id);
  }

  @DeleteMapping("/{id}")
  public void deleteOne(@PathVariable UUID id) {
    Book book = bookService.findById(id);
    if (book == null) {
      throw new NotFoundException("Book with id " + id + " not found");
    }
    bookService.deleteById(id);
  }

  @PutMapping("{bookId}/author/{authorId}")
  public Book assignAuthorToBook(@PathVariable UUID bookId, @PathVariable UUID authorId) {
    return bookService.assignAuthorToBook(bookId, authorId);
  }
}
