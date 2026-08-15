package hr.tis.academy.sightseeingapp.service.impl;

import hr.tis.academy.sightseeingapp.dto.AttractionRowDto;
import hr.tis.academy.sightseeingapp.dto.NewAttractionFormDto;
import hr.tis.academy.sightseeingapp.model.Attraction;
import hr.tis.academy.sightseeingapp.model.AttractionMetadata;
import hr.tis.academy.sightseeingapp.model.Location;
import hr.tis.academy.sightseeingapp.repository.AttractionMetadataRepository;
import hr.tis.academy.sightseeingapp.repository.LocationRepository;
import hr.tis.academy.sightseeingapp.service.AttractionBrowseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttractionBrowseServiceImpl implements AttractionBrowseService {

    private final AttractionMetadataRepository attractionMetadataRepository;
    private final LocationRepository locationRepository;

    public AttractionBrowseServiceImpl(AttractionMetadataRepository attractionMetadataRepository,
                                       LocationRepository locationRepository) {
        this.attractionMetadataRepository = attractionMetadataRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<AttractionRowDto> getAllAttractionRows() {
        List<AttractionRowDto> rows = new ArrayList<>();

        for (AttractionMetadata metadata : attractionMetadataRepository.findAll()) {
            String place = metadata.getLocation().getName();
            for (Attraction attraction : metadata.getAttractions()) {
                rows.add(new AttractionRowDto(
                        place,
                        attraction.getName(),
                        attraction.getDescription(),
                        attraction.getType().name()
                ));
            }
        }
        return rows;
    }

    @Override
    @Transactional
    public void createAttraction(NewAttractionFormDto form) {
        Attraction attraction = new Attraction();
        attraction.setName(form.getName());
        attraction.setDescription(form.getDescription());
        attraction.setType(form.getType());

        AttractionMetadata metadata = attractionMetadataRepository.findByLocation(form.getPlace());

        if (metadata != null) {
            metadata.getAttractions().add(attraction);
            attractionMetadataRepository.save(metadata);
        } else {
            Location location = new Location();
            location.setName(form.getPlace());

            AttractionMetadata newMetadata = new AttractionMetadata();
            newMetadata.setLocation(location);
            newMetadata.setAttractions(new ArrayList<>(List.of(attraction)));

            attractionMetadataRepository.save(newMetadata);
        }
    }
}