package hr.tis.academy.sightseeingapp.service.impl;

import hr.tis.academy.sightseeingapp.dto.AttractionDto;
import hr.tis.academy.sightseeingapp.dto.AttractionMetadataDto;
import hr.tis.academy.sightseeingapp.mapper.AttractionMapper;
import hr.tis.academy.sightseeingapp.mapper.AttractionMetadataMapper;
import hr.tis.academy.sightseeingapp.model.Attraction;
import hr.tis.academy.sightseeingapp.model.AttractionMetadata;
import hr.tis.academy.sightseeingapp.model.Location;
import hr.tis.academy.sightseeingapp.repository.AttractionMetadataRepository;
import hr.tis.academy.sightseeingapp.repository.exception.NoAttractionFoundException;
import hr.tis.academy.sightseeingapp.service.AttractionMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AttractionMetadataServiceImpl implements AttractionMetadataService {
    private final AttractionMetadataRepository attractionMetadataRepository;
    private final AttractionMetadataMapper attractionMetadataMapper;

    public AttractionMetadataServiceImpl(AttractionMetadataMapper attractionMetadataMapper, AttractionMetadataRepository attractionMetadataRepository) {
        this.attractionMetadataRepository = attractionMetadataRepository;
        this.attractionMetadataMapper = attractionMetadataMapper;
    }

    @Override
    public List<AttractionMetadataDto> findByLocation(String location) {
        List<AttractionMetadata> attractionMetadata = attractionMetadataRepository.findByLocation(location);

        if (attractionMetadata.isEmpty()) {
            throw new NoAttractionFoundException("No Attraction with localtion " + location);
        }
        return attractionMetadata.stream()
                .map(attractionMetadataMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public AttractionMetadataDto save(AttractionMetadataDto attractionMetadataDto) {
        AttractionMetadata attractionMetadataEntity = attractionMetadataMapper.toEntity(attractionMetadataDto);
        AttractionMetadata savedAttractionMetadata = attractionMetadataRepository.save(attractionMetadataEntity);
        return attractionMetadataMapper.toDto(savedAttractionMetadata);
    }
}
