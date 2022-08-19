package com.metaxiii.fr.goodapi.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiExceptionHandler {
    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<ErrorCodeDetail> handleServiceException(final EmployeeException exception) {
        return new ResponseEntity<>(exception.getDetails(),
                HttpStatus.valueOf(exception.getDetails().getCodeDetails()));
    }

    @ExceptionHandler(DatabindingException.class)
    public ResponseEntity<String> handleServiceExceptionData(final DatabindingException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceModifiedException.class)
    public ResponseEntity<String> handleServiceExceptionResourceModified(final ResourceModifiedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
