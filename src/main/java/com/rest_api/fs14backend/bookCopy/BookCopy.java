package com.rest_api.fs14backend.bookCopy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rest_api.fs14backend.book.Book;
import com.rest_api.fs14backend.checkout.Checkout;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book_copy")
public class BookCopy {
  @Id
  @UuidGenerator
  @GeneratedValue
  private UUID id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "book_id")
  private Book book;

  @OneToMany(mappedBy = "bookCopy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Checkout> checkoutList;

  public BookCopy(Book book) {
    this.book = book;
  }
}
