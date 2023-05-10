package com.rest_api.fs14backend.bookCopy;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCopyDTO {
  private UUID id;

  @NotNull(message = "BookId is mandatory")
  private UUID bookId;


  public BookCopyDTO(UUID bookId) {
    this.bookId = bookId;
  }
}
