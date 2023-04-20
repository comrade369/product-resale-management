package com.practicespringboot.ProductResaleManagement.exceptions;

import com.practicespringboot.ProductResaleManagement.payloads.ApiMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiMessage> resourceNotFoundException(ResourceNotFoundException ex) {
        String message = ex.getMessage();
        ApiMessage apiMessage = new ApiMessage(message, false);
        return new ResponseEntity<>(apiMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> httpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>("Please entry the value for the key !!!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValid(MethodArgumentNotValidException ex)
    {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->
        {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> sqlIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        return new ResponseEntity<>("Value of entry is already taken !!!", HttpStatus.BAD_REQUEST);
    }
}
