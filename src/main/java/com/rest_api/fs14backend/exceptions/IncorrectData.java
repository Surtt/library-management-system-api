package com.rest_api.fs14backend.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class IncorrectData {
  private String errorMessage;
}
