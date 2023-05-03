package com.rest_api.fs14backend.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @GetMapping
  public List<Category> findAll() {
    return categoryService.findAll();
  }

  @GetMapping("/{id}")
  public Optional<Category> findOne(@PathVariable UUID id) {
    return categoryService.findById(id);
  }

  @PostMapping
  public Category createOne(@RequestBody Category category) {
    return categoryService.createOne(category);
  }

  @PutMapping("/{id}")
  public Category updateOne(@RequestBody Category category, @PathVariable UUID id) {
    return categoryService.updateOne(category, id);
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable UUID id) {
    categoryService.deleteById(id);
  }
}
