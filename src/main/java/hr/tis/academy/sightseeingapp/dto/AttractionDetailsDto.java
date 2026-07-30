package hr.tis.academy.sightseeingapp.dto;

import hr.tis.academy.sightseeingapp.enums.Type;

import java.math.BigDecimal;
import java.util.List;

public record AttractionDetailsDto(
        String name,
        String description,
        Type type,
        BigDecimal averageRating,
        List<ReviewDto> reviews
) {}
