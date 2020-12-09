package com.griddynamics.exceptions;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ResponseEntityCustomObject {
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime zonedDateTime;

    public ResponseEntityCustomObject(String message, HttpStatus httpStatus, ZonedDateTime zonedDateTime) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.zonedDateTime = zonedDateTime;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    @Override
    public String toString() {
        return "Message: " + message + "\r\n" +
                "HttpStatus: " + httpStatus + "\r\n" +
                "ZonedDateTime: " + zonedDateTime;
    }
}
