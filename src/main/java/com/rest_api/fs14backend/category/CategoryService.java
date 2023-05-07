package com.rest_api.fs14backend.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  public List<Category> findAll() {
    return categoryRepository.findAll();
  }

  public Category findById(UUID id) {
    return categoryRepository.findById(id)
            .orElse(null);
  }

  public void deleteById(UUID id) {
    categoryRepository.deleteById(id);
  }

  public Category createOne(Category category) {
    return categoryRepository.save(category);
  }

  public Category updateOne(Category newCategory, UUID id) {
    return categoryRepository.findById(id)
            .map(category -> {
              category.setName(newCategory.getName());
              return categoryRepository.save(category);
            })
            .orElseGet(() -> categoryRepository.save(newCategory));
  }
}
