package com.griddynamics.exceptions;

/**
 * {@code MappingException} - kind of {@link Exception} that used in
 * {@link com.griddynamics.mappers.EntityMapper} implementation classes.
 */

public class MappingException extends ServiceException {
    public MappingException(String message) {
        super(message);
    }

    public MappingException(String message, Throwable cause) {
        super(message, cause);
    }

    public MappingException(Throwable cause) {
        super(cause);
    }
}
