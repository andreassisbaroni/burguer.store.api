package br.com.andrebaroni.burger.store.api.application.controller.advice.details;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ExceptionDetails {

    private LocalDateTime timestamp;
    private String error;
    private String message;
    private HttpStatus status;

    private ExceptionDetails() {
        super();
        this.setTimestamp(LocalDateTime.now());
    }

    public ExceptionDetails(String error, String message, HttpStatus status) {
        this();
        this.setError(error);
        this.setMessage(message);
        this.setStatus(status);
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
