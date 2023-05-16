package com.rest_api.fs14backend.category;

import com.rest_api.fs14backend.book.BookService;
import com.rest_api.fs14backend.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/categories")
public class CategoryController {
  private final CategoryService categoryService;
  private final BookService bookService;

  @GetMapping
  public List<Category> findAll() {
    return categoryService.findAll();
  }

  @GetMapping("/{id}")
  public Category findById(@PathVariable UUID id) {
    Category category = categoryService.findById(id);
    if (category == null) {
      throw new NotFoundException("Category with id " + id + " not found");
    }
    return category;
  }

  @PostMapping
  public Category createOne(@RequestBody Category category) {
    return categoryService.createOne(category);
  }

  @PutMapping("/{id}")
  public Category updateOne(@RequestBody Category newCategory, @PathVariable UUID id) {
    Category category = categoryService.findById(id);
    if (category == null) {
      throw new NotFoundException("Category with id " + id + " not found");
    }
    return categoryService.updateOne(newCategory, id);
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable UUID id) throws Exception {
    Category category = categoryService.findById(id);
    if (category == null) {
      throw new NotFoundException("Category with id " + id + " not found");
    }
    categoryService.deleteById(id);
  }
}
