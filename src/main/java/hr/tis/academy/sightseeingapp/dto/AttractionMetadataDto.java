package hr.tis.academy.sightseeingapp.dto;

import hr.tis.academy.sightseeingapp.model.Location;

import java.util.List;

public record AttractionMetadataDto(
        LocationDto location,
        List<AttractionDto> attractions
) {}
