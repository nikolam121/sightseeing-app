package hr.tis.academy.sightseeingapp.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ReviewDto(
        String location,
        String attractionName,
        LocalDateTime timestamp,
        BigDecimal rating,
        String reviewText
) {
}