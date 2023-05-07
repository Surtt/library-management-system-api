package com.rest_api.fs14backend.book;

import com.rest_api.fs14backend.author.Author;
import com.rest_api.fs14backend.category.Category;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity(name = "book")
@Table(name = "book")
@Data
@NoArgsConstructor
public class Book {
  @Id
  @UuidGenerator
  @GeneratedValue
  private UUID id;

  @Column(nullable = false, unique = true, columnDefinition = "varchar(17)")
  private String isbn;

  @Column(nullable = false, columnDefinition = "varchar(50)")
  private String title;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false, columnDefinition = "varchar(20)")
  private String publisher;

  @Column(nullable = false, columnDefinition = "boolean default true")
  private Boolean status = true;

  @Temporal(TemporalType.DATE)
  private Date publishedDate;

  @Column(nullable = false, columnDefinition = "integer default 1")
  private Integer quantity;

  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  private Category category;

  @ManyToMany
  @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
  private Set<Author> authors = new HashSet<>();

  public Book(String isbn, String title, String description, String publisher, Boolean status, Date publishedDate, Integer quantity, Category category) {
    this.isbn = isbn;
    this.title = title;
    this.description = description;
    this.publisher = publisher;
    this.status = status;
    this.publishedDate = publishedDate;
    this.quantity = quantity;
    this.category = category;
  }
}
