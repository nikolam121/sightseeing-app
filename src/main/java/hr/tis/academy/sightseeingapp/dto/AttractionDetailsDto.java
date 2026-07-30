package hr.tis.academy.sightseeingapp.dto;

import java.util.List;

public record AttractionDetailsDto(
        String name,
        String description,
        String type,
        Double averageRating,
        List<ReviewDto> reviews
) {}
