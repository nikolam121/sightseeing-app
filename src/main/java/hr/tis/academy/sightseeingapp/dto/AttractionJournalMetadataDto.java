package hr.tis.academy.sightseeingapp.dto;

import hr.tis.academy.sightseeingapp.model.Attraction;
import hr.tis.academy.sightseeingapp.model.Location;

import java.time.LocalDate;

public record AttractionJournalMetadataDto(
        String location,
        String attraction,
        String comment,
        LocalDate date
) {
}
