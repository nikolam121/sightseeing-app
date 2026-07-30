package hr.tis.academy.sightseeingapp.mapper;

import hr.tis.academy.sightseeingapp.dto.AttractionDto;
import hr.tis.academy.sightseeingapp.model.Attraction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AttractionMapper {
    @Mapping(ignore = true, target = "id")
    AttractionDto toDto(Attraction attraction);
    Attraction toEntity(AttractionDto attractionDto);
}
