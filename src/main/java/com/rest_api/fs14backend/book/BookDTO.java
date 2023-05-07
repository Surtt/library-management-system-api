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
  private String publisher;

  @NotNull(message = "status is mandatory")
  private Boolean status;
  private Date publishedDate;

  @NotNull(message = "quantity is mandatory")
  private Integer quantity;

  @NotNull(message = "categoryId is mandatory")
  private UUID categoryId;

  public BookDTO(UUID categoryId) {
    this.categoryId = categoryId;
  }
}
