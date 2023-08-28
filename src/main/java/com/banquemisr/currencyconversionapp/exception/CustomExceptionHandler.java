package com.banquemisr.currencyconversionapp.exception;

import com.banquemisr.currencyconversionapp.entities.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(value = BadEntryException.class)
    public ResponseEntity<Object> badEntryException(BadEntryException exception) {
        Response<Object> response = Response.builder().message(exception.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .build();
        return ResponseEntity.status(response.statusCode()).body(response);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException exception) {
        Response<Object> response = Response
                .builder()
                .message(exception.getMessage())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND.name())
                .build();

        return ResponseEntity.status(response.statusCode()).body(response);
    }

    @ExceptionHandler(value = {Exception.class, Throwable.class})
    public ResponseEntity<Object> handleGenericException(Exception exception) {
        Response<Object> response = Response
                .builder()
                .message("An unexpected error occurred")
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .build();

        return ResponseEntity.status(response.statusCode()).body(response);
    }
}
