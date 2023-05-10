package com.rest_api.fs14backend.checkout;

import com.rest_api.fs14backend.bookCopy.BookCopy;
import com.rest_api.fs14backend.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "checkout")
public class Checkout {
  @Id
  @UuidGenerator
  @GeneratedValue
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "book_copy_id")
  private BookCopy bookCopy;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @DateTimeFormat
  private Date borrowDate = new Date();

  @DateTimeFormat
  private Date returnDate;

  public Checkout(BookCopy bookCopy, User user) {
    this.bookCopy = bookCopy;
    this.user = user;
  }
}
