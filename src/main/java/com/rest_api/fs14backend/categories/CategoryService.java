package com.rest_api.fs14backend.categories;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
    @Transactional
    public void deleteByName(String categoryName) {
        System.out.println("dele by name is called" + categoryName);
        categoryRepository.deleteByCategoryname(categoryName);
    }

    public Category createOne(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional
    public Category updateCategory(String categoryName, Category newCategory) {
        Category existingCategory = categoryRepository.findByCategoryname(categoryName);
        if (existingCategory == null) {
            return null;
        }
        existingCategory.setCategoryname(newCategory.getCategoryname());
        Category updatedCategory = categoryRepository.save(existingCategory);
        return updatedCategory;
    }
}
