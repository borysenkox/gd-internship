package com.griddynamics.controllers.exceptionHandlers;

import com.griddynamics.exceptions.BadIdException;
import com.griddynamics.exceptions.ProductNotFoundException;
import com.griddynamics.exceptions.ResponseEntityCustomObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;

@Slf4j
@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    ResponseEntity<ResponseEntityCustomObject> handleProductNotFoundException(ProductNotFoundException e) {
        ResponseEntityCustomObject exception = new ResponseEntityCustomObject(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now()
        );

        log.info("Throw ProductNotFoundException", e);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
    }

    @ExceptionHandler(BadIdException.class)
    ResponseEntity<ResponseEntityCustomObject> handleBadIdException(BadIdException e) {
        ResponseEntityCustomObject exception = new ResponseEntityCustomObject(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now()
        );

        log.error("Throw BadIdException", e);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
    }
}
