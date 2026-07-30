package hr.tis.academy.sightseeingapp.mapper;

import hr.tis.academy.sightseeingapp.dto.LocationDto;
import hr.tis.academy.sightseeingapp.model.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    @Mapping(ignore = true, target = "id")
    LocationDto toDto(Location location);
    Location toEntity(LocationDto dto);
}
