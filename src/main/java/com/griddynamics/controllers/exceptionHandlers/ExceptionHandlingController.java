package com.griddynamics.controllers.exceptionHandlers;

import com.griddynamics.exceptions.BadIdException;
import com.griddynamics.exceptions.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    String handleProductNotFoundException(ProductNotFoundException e) {

        log.error("Throw ProductNotFoundException", e);

        return e.getCause().getMessage();
    }

    @ExceptionHandler(BadIdException.class)
    String handleBadIdException(BadIdException e) {

        log.error("Throw BadIdException", e);

        return e.getCause().getMessage();
    }
}
