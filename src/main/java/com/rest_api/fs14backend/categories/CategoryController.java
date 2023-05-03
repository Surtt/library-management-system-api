package com.rest_api.fs14backend.categories;

import com.rest_api.fs14backend.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
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
    public Category findOne(@PathVariable UUID id) {
        Category category = categoryService.findById(id);

        if (category == null) {
            throw new NotFoundException("Category not found");
        }
        return category;
    }

    @PostMapping
    public Category createOne(@RequestBody Category category) {
        return categoryService.createOne(category);
    }

    @PutMapping("/{id}")
    public Category updateOne(@RequestBody Category category, @PathVariable UUID id) {
        if (category == null) {
            throw new NotFoundException("Category not found");
        }
        return categoryService.updateOne(category, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        Category category = categoryService.findById(id);
        if (category == null) {
            throw new NotFoundException("Category not found");
        }
        categoryService.deleteById(id);
    }
}
