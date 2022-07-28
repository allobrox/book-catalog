package me.tamasrigoczki.bookCatalogApi.controller.exceptionHandler;

import me.tamasrigoczki.bookCatalogApi.model.dto.error.BookAlreadyExistsError;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BookCatalogExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {IllegalArgumentException.class, IllegalStateException.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        BookAlreadyExistsError bodyOfResponse =
                new BookAlreadyExistsError(ex.getMessage());
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse(ex.getMessage());
        return response(ex, request, HttpStatus.BAD_REQUEST, errorMessage);
    }

    private ResponseEntity<Object> response(Exception ex, WebRequest request,
                                            HttpStatus status,
                                            String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cause", message);
        return new ResponseEntity<>(null, headers, status);
    }

}
