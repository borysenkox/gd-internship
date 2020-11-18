package com.griddynamics.exceptions;

/**
 * {@code MappingException} - kind of {@link Exception} that used in
 * {@link com.griddynamics.mappers.EntityMapper} implementation classes.
 */

public class MappingException extends RuntimeException {
    public MappingException(String message) {
        super(message);
    }

    public MappingException(String message, Throwable cause) {
        super(message, cause);
    }

    public MappingException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public String getLocalizedMessage() {
        return super.getLocalizedMessage();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
