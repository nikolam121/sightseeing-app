package hr.tis.academy.sightseeingapp.mapper;

import hr.tis.academy.sightseeingapp.dto.AttractionDto;
import hr.tis.academy.sightseeingapp.model.Attraction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {LocationMapper.class})
public interface AttractionMapper {
    AttractionDto toDto(Attraction attraction);

    @Mapping(ignore = true, target = "id")
    Attraction toEntity(AttractionDto attractionDto);

    List<AttractionDto> toDtoList(List<Attraction> attractions);
}
