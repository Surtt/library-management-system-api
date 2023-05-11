package com.rest_api.fs14backend.book;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
  private UUID id;

  @NotNull(message = "isbn is mandatory")
  private String isbn;

  @NotNull(message = "title is mandatory")
  private String title;

  private String description;

  private String image;

  private String publisher;

  private Date publishedDate;

  @NotNull(message = "categoryId is mandatory")
  private UUID categoryId;

  @NotNull(message = "authorId is mandatory")
  private UUID authorId;

  public BookDTO(UUID categoryId, UUID authorId) {
    this.categoryId = categoryId;
    this.authorId = authorId;
  }
}
