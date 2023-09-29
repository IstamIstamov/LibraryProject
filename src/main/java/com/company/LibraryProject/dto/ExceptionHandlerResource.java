package com.company.LibraryProject.dto;

import jakarta.validation.UnexpectedTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerResource {

    @ExceptionHandler
    public ResponseEntity<ResponseDto<Void>> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(
                ResponseDto.<Void>builder()
                        .message("Validation error")
                        .code(-2)
                        .errors(
                                e.getBindingResult().getFieldErrors()
                                        .stream()
                                        .map(fieldError -> {
                                                    String field = fieldError.getField();
                                                    String message = fieldError.getDefaultMessage();
                                                    String rejectionValue = String.valueOf(fieldError.getRejectedValue());
                                                    return new ErrorDto(field, message + " rejection value: " + rejectionValue);
                                                }
                                        )
                                        .toList()
                        )
                        .build()
        );
    }

    @ExceptionHandler
    public ResponseEntity<ResponseDto<Void>> unexpectedTypeException(UnexpectedTypeException e) {
        List<ErrorDto> errors = new ArrayList<>();
        errors.add(new ErrorDto("rejection", e.getMessage()));
        return ResponseEntity.badRequest().body(
                ResponseDto.<Void>builder()
                        .message("Validation error")
                        .code(-2)
                        .errors(errors)
                        .build()
        );
    }

}
