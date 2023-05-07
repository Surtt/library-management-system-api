package com.rest_api.fs14backend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RequestValidationErrorDTO {
  private List<FieldValidationErrorDTO> errors;
}
