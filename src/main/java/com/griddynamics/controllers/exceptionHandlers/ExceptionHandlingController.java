package com.griddynamics.controllers.exceptionHandlers;

import com.griddynamics.exceptions.BadIdException;
import com.griddynamics.exceptions.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException e) {

        log.error("Throw ProductNotFoundException", e);

        return new ResponseEntity<>(e.getCause().getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadIdException.class)
    ResponseEntity<String> handleBadIdException(BadIdException e) {

        log.error("Throw BadIdException", e);

        return new ResponseEntity<>(e.getCause().getMessage(), HttpStatus.BAD_REQUEST);
    }
}
