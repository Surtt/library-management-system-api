package com.rest_api.fs14backend.category;

import com.rest_api.fs14backend.book.Book;
import com.rest_api.fs14backend.book.BookRepository;
import com.rest_api.fs14backend.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@Service
public class CategoryService {
  private final CategoryRepository categoryRepository;
  private final BookRepository bookRepository;

  public List<Category> findAll() {
    return categoryRepository.findAll();
  }

  public Category findById(UUID id) {
    return categoryRepository.findById(id).orElse(null);
  }

  public void deleteById(UUID id) throws Exception {
    Book book = bookRepository.findAll().stream().filter(b -> Objects.equals(b.getCategory(), id)).findFirst()
            .orElse(null);

    if (book != null) {
      throw new NotFoundException("It is not possible to delete the category because it is associated with a book");
    } else {
      categoryRepository.deleteById(id);
    }
  }

  public Category createOne(Category category) {
    return categoryRepository.save(category);
  }

  public Category updateOne(Category newCategory, UUID id) {
    return categoryRepository.findById(id).map(category -> {
      category.setName(newCategory.getName());
      return categoryRepository.save(category);
    }).orElseGet(() -> categoryRepository.save(newCategory));
  }
}
