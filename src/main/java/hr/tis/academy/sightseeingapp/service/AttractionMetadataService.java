package hr.tis.academy.sightseeingapp.service;

import hr.tis.academy.sightseeingapp.dto.AttractionMetadataDto;

public interface AttractionMetadataService {
    AttractionMetadataDto findByLocation(String location);
    AttractionMetadataDto save(AttractionMetadataDto attractionMetadataDto);
}
