package com.rest_api.fs14backend.book;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;


@Entity(name = "book")
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
  @Id
  @Column
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
  private boolean status = true;
  @Temporal(TemporalType.DATE)
  private Date publishedDate;
  @Column(nullable = false, columnDefinition = "integer default 1")
  private Integer quantity;
}
