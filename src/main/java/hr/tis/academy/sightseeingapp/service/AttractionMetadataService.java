package hr.tis.academy.sightseeingapp.service;

import hr.tis.academy.sightseeingapp.dto.AttractionDto;
import hr.tis.academy.sightseeingapp.dto.AttractionMetadataDto;
import hr.tis.academy.sightseeingapp.model.Location;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AttractionMetadataService {
    AttractionMetadataDto findByLocation(String location);
    AttractionMetadataDto save(AttractionMetadataDto attractionMetadataDto);
}
