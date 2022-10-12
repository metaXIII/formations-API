package com.metaxiii.fr.goodapi.exception;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@ApiResponses(value = {
        @ApiResponse(responseCode = "400",  description = "Bad Request", content = @Content),
        @ApiResponse(responseCode = "403",  description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404",  description = "Resource not found", content = @Content)
})
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
