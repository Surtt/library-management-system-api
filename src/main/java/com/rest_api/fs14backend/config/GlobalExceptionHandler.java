package com.rest_api.fs14backend.config;

import com.rest_api.fs14backend.exceptions.FieldValidationErrorDTO;
import com.rest_api.fs14backend.exceptions.IncorrectData;
import com.rest_api.fs14backend.exceptions.NotFoundException;
import com.rest_api.fs14backend.exceptions.RequestValidationErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler
  public ResponseEntity<RequestValidationErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    List<FieldValidationErrorDTO> errors = ex.getFieldErrors()
            .stream()
            .map(error -> new FieldValidationErrorDTO(error.getField(), error.getDefaultMessage()))
            .toList();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new RequestValidationErrorDTO(errors));
  }

  @ExceptionHandler
  public ResponseEntity<IncorrectData> handlerException(NotFoundException exception) {
    IncorrectData data = new IncorrectData();
    data.setErrorMessage(exception.getMessage());
    return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
  }
}
