package com.picpaychallenger.infra.config;

import com.picpaychallenger.dto.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppError {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity throwDuplicateEntity(DataIntegrityViolationException exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO("User already exists", "400");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity entityNotFoundException(EntityNotFoundException exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO("User not found!", "404");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity generalException(Exception exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "500");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }
}
