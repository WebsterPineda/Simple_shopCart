package com.webster.response;

import com.webster.exceptions.BaseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@ControllerAdvice
@RestController
public class CustomNotFoundResponse extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BaseNotFoundException.class)
    public final ResponseEntity<NotFoundErrorResponse> handleNotFoundExceptions(BaseNotFoundException ex, WebRequest req) {
        NotFoundErrorResponse errDetails = new NotFoundErrorResponse(Instant.now(),
                ex.getMessage(), req.getDescription(false)
                .replace("uri=", ""));

        return new ResponseEntity<>(errDetails, HttpStatus.NOT_FOUND);
    }
}
