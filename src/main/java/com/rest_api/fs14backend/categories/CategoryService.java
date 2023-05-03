package com.rest_api.fs14backend.categories;

import com.rest_api.fs14backend.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  public List<Category> findAll() {
    return categoryRepository.findAll();
  }

  public Optional<Category> findById(UUID id) {
    Optional<Category> categoryOptional = categoryRepository.findById(id);
    if (categoryOptional.isEmpty()) {
      throw new NotFoundException("Category not found");
    }
    return categoryRepository.findById(id);
  }

  public void deleteById(UUID id) {
    Optional<Category> categoryOptional = categoryRepository.findById(id);
    if (categoryOptional.isEmpty()) {
      throw new NotFoundException("Category not found");
    }
    categoryRepository.deleteById(id);
  }

  public Category createOne(Category category) {
    return categoryRepository.save(category);
  }

  public Category updateOne(Category newCategory, UUID id) {
    Optional<Category> categoryOptional = categoryRepository.findById(id);
    if (categoryOptional.isEmpty()) {
      throw new NotFoundException("Category not found");
    }

    return categoryRepository.findById(id).map(category -> {
      category.setName(newCategory.getName());
      return categoryRepository.save(category);
    }).orElseGet(() -> categoryRepository.save(newCategory));
  }
}
