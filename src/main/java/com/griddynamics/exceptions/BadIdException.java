package com.griddynamics.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Id value is null or negative")
@NoArgsConstructor
public class BadIdException extends RuntimeException {

    public BadIdException(String message) {
        super(message);
    }

    public BadIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadIdException(Throwable cause) {
        super(cause);
    }
}
