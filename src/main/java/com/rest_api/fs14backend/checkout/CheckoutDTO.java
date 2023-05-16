package com.rest_api.fs14backend.checkout;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutDTO {
  private UUID id;

  @NotNull(message = "Book Copy Id is mandatory")
  private UUID bookCopyId;

  @NotNull(message = "User Id is mandatory")
  private UUID userId;
}
