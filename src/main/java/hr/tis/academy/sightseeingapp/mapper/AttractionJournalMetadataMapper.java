package hr.tis.academy.sightseeingapp.mapper;


import hr.tis.academy.sightseeingapp.dto.AttractionJournalMetadataDto;
import hr.tis.academy.sightseeingapp.model.AttractionJournalMetadata;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AttractionJournalMetadataMapper {
    @Mapping(source = "location.name", target = "location")
    @Mapping(source = "attraction.name", target = "attraction")
    AttractionJournalMetadataDto toDto(AttractionJournalMetadata attractionJournalMetadata);

    @Mapping(ignore = true, target = "id")
    @Mapping(source = "location", target = "location.name")
    @Mapping(source = "attraction", target = "attraction.name")
    AttractionJournalMetadata toEntity(AttractionJournalMetadataDto attractionJournalMetadataDto);
}
