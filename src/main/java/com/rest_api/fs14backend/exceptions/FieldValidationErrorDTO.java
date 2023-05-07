package com.rest_api.fs14backend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldValidationErrorDTO {
  private String fieldName;
  private String errorMessage;
}
