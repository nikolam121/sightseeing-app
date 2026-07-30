package hr.tis.academy.sightseeingapp.mapper;

import hr.tis.academy.sightseeingapp.dto.AttractionMetadataDto;
import hr.tis.academy.sightseeingapp.model.AttractionMetadata;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {AttractionMapper.class, LocationMapper.class})
public interface AttractionMetadataMapper {
    AttractionMetadataDto toDto(AttractionMetadata attractionMetadata);

    @Mapping(ignore = true, target = "id")
    AttractionMetadata toEntity(AttractionMetadataDto attractionMetadataDto);
}
