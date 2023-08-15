package com.example.airlineticketsystem.controllers.exceptionHandlers;


import com.example.airlineticketsystem.customExceptions.ApplicationException;
import com.example.airlineticketsystem.dtos.requests.BaseResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.MessageFormat;
import java.util.Optional;


@RestControllerAdvice
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers,
                                                                   HttpStatusCode status,
                                                                   WebRequest request) {

        final var errorMessage =
                MessageFormat.format("No handler found for {0} {1}", ex.getHttpMethod(), ex.getRequestURL());
        System.out.println(errorMessage);
        var response = BaseResponse.<Object>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(errorMessage)
                .isSuccess(false)
                .build();
        return ResponseEntity.badRequest().body(response);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        final String errorMessage = ex.getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .toList()
                .toString();

        System.out.println(errorMessage);
        var response = BaseResponse.<Object>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(errorMessage)
                .isSuccess(false)
                .build();
        return ResponseEntity.badRequest().body(response);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(final EntityNotFoundException entityNotFoundException, final WebRequest request) {
        System.out.println("Entity could not be found: " + entityNotFoundException.getMessage()
                + request.getHeader("client-type"));
        var response = BaseResponse.<Object>builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(entityNotFoundException.getMessage())
                .isSuccess(false)
                .build();
        return ResponseEntity.of(Optional.ofNullable(response));
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Object> handleApplicationException(final ApplicationException e, final WebRequest request) {
        System.out.println("An error has occurred: " + e.getMessage()
                + request.getHeader("client-type"));
        var response = BaseResponse.<Object>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(e.getMessage())
                .isSuccess(false)
                .build();
        return ResponseEntity.badRequest().body(response);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(final ConstraintViolationException e, final WebRequest request) {
        String message = e.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList()
                .toString();
        System.out.println("Constraint violation occurred: " + message
                + request.getHeader("client-type"));
        var response = BaseResponse.<Object>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(message)
                .isSuccess(false)
                .build();
        return ResponseEntity.badRequest().body(response);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(final DataIntegrityViolationException e, final WebRequest request) {

        System.out.println("Data integrity violation, " +
                "You perhaps tried to add a duplicate record to a unique key column: " + e.getMessage()
                + request.getHeader("client-type"));
        var response = BaseResponse.<Object>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(e.getMessage())
                .isSuccess(false)
                .build();
        return ResponseEntity.badRequest().body(response);

    }


}
