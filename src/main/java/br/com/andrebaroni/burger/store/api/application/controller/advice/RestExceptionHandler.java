package br.com.andrebaroni.burger.store.api.application.controller.advice;

import br.com.andrebaroni.burger.store.api.application.controller.advice.details.ExceptionDetails;
import br.com.andrebaroni.burger.store.api.domain.exception.EntityNotFoundException;
import br.com.andrebaroni.burger.store.api.domain.exception.GenericException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ExceptionDetails> handleGenericException(GenericException genericException) {
        ExceptionDetails details = new ExceptionDetails(genericException.getClass().getSimpleName(), genericException.getMessage(), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleEntityNotFoundException(EntityNotFoundException entityNotFoundException) {
        ExceptionDetails details = new ExceptionDetails(entityNotFoundException.getClass().getSimpleName(), entityNotFoundException.getMessage(), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }
}
