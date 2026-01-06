package dev.medina.portfolio_api.controllers.exceptions;

import dev.medina.portfolio_api.enums.exceptions.CategoryException;
import dev.medina.portfolio_api.services.exceptions.InvalidDateException;
import dev.medina.portfolio_api.services.exceptions.ProjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.time.format.DateTimeParseException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<StandardError> projectNotFound(ProjectNotFoundException e, HttpServletRequest request) {
        String error = "Project not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError se = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(se);
    }

    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<StandardError> invalidCategory(CategoryException c, HttpServletRequest request) {
        String error = "Invalid category";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError se = new StandardError(Instant.now(), status.value(), error, c.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(se);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<StandardError> dateTimeParse(DateTimeParseException e, HttpServletRequest request) {
        String error = "Date cant't be parsed - parse format is: day/month/year.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError se = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(se);
    }

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<StandardError> invalidDate(InvalidDateException e, HttpServletRequest request) {
        String error = "Date is outside the range";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError se = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(se);
    }


}
