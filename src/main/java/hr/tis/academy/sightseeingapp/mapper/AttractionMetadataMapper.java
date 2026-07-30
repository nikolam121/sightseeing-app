package hr.tis.academy.sightseeingapp.mapper;

import hr.tis.academy.sightseeingapp.dto.AttractionMetadataDto;
import hr.tis.academy.sightseeingapp.model.AttractionMetadata;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AttractionMapper.class, LocationMapper.class})
public interface AttractionMetadataMapper {
    AttractionMetadataDto toDto(AttractionMetadata attractionMetadata);
    AttractionMetadata toEntity(AttractionMetadataDto attractionMetadataDto);
}
