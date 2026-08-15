package hr.tis.academy.sightseeingapp.configuration;

import hr.tis.academy.sightseeingapp.repository.exception.NoAttractionFoundException;
import hr.tis.academy.sightseeingapp.repository.exception.UserAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;
import java.util.UUID;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAnyException(Exception exception) {
        String uuid = UUID.randomUUID().toString();
        LOGGER.error("Unhandled exception, reference id '{}'", uuid, exception);

        return ResponseEntity.internalServerError()
                .body(String.format("An unexpected error occurred. Reference ID: %s", uuid));
    }

    @ExceptionHandler(NoAttractionFoundException.class)
    public ResponseEntity<String> handleNoAttractionFoundException(NoAttractionFoundException exception) {
        LOGGER.warn("Attraction not found: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException exception) {
        LOGGER.warn("Requested record does not exist: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested record does not exist.");
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        LOGGER.warn("User already exists: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }
}
