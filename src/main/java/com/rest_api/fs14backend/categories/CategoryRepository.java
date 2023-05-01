package com.rest_api.fs14backend.categories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;


public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Modifying
    @Transactional
    void deleteByCategoryname(String categoryname);

    Category findByCategoryname(String categoryName);
}
