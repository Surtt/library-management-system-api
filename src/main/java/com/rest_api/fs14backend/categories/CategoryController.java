package com.rest_api.fs14backend.categories;

import com.rest_api.fs14backend.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category findOne(@PathVariable Long id){
        Category category = categoryService.findById(id);

        if (category == null) {
            throw new NotFoundException("Category not found");
        }
        return category;
    }
    //DEVTODO the database table Categories needs to have unique values under categoryname
    @PostMapping("/")
    public Category createOne(@RequestBody Category category) {
            return categoryService.createOne(category);
        }

    @DeleteMapping("/{categoryname}")
    public void delete(@PathVariable String categoryname) {

        categoryService.deleteByName(categoryname);
    }

    @PutMapping("/{categoryname}") //url/api/v1/category/science + Request Body
    public ResponseEntity<Category> updateOne(@PathVariable String categoryname, @RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategory(categoryname, category);
        if (updatedCategory == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedCategory);
        }
    }
}
