package hr.tis.academy.sightseeingapp.dto;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record HttpErrorDto (
        UUID uuid,
        LocalDateTime localDateTime,
        String message,
        HttpStatus status,
        String request
) {
}
