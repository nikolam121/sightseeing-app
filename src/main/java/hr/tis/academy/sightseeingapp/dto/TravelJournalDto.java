package hr.tis.academy.sightseeingapp.dto;

import hr.tis.academy.sightseeingapp.model.Attraction;
import hr.tis.academy.sightseeingapp.model.AttractionJournalMetadata;

import java.time.LocalDate;
import java.util.List;

public record TravelJournalDto(
        LocalDate startDate,
        LocalDate endDate,
        String description,
        List<AttractionJournalMetadataDto> attractions
) {
}
