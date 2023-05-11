package com.rest_api.fs14backend.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rest_api.fs14backend.author.Author;
import com.rest_api.fs14backend.base.BaseEntity;
import com.rest_api.fs14backend.bookCopy.BookCopy;
import com.rest_api.fs14backend.category.Category;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;


@Entity(name = "book")
@Table(name = "book")
@Data
@NoArgsConstructor
public class Book extends BaseEntity {
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

  @DateTimeFormat
  private Date publishedDate;

  @Column(nullable = false, columnDefinition = "integer default 0")
  private Integer quantity = 0;

  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToMany
  @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
  private Set<Author> authors = new HashSet<>();

  @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JsonIgnore
  private List<BookCopy> bookCopyList = new ArrayList<>();

  public Book(String isbn, String title, String description, String publisher, Date publishedDate, Category category, UUID authorId) {
    this.isbn = isbn;
    this.title = title;
    this.description = description;
    this.publisher = publisher;
    this.publishedDate = publishedDate;
    this.category = category;
  }

  public void addAuthor(Author author) {
    this.authors.add(author);
    author.getBooks()
            .add(this);
  }

  public void removeAuthor(UUID authorId) {
    Author author = this.authors.stream()
            .filter(a -> a.getId() == authorId)
            .findFirst()
            .orElse(null);
    if (author != null) {
      this.authors.remove(author);
      author.getBooks()
              .remove(this);
    }
  }

  public void decreaseBookQuantity() {
    int length = this.getBookCopyList()
            .size();
    this.setQuantity(length);
  }

  public void increaseBookQuantity() {
    int length = this.getBookCopyList()
            .size();
    this.setQuantity(length);
  }
}
