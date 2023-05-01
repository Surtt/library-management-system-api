package com.rest_api.fs14backend.categories;

import jakarta.persistence.*;

@Entity(name = "categories")
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, columnDefinition = "varchar(50)", name="categoryname")
    private String categoryname;

    public Category(){};

    public Category(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
