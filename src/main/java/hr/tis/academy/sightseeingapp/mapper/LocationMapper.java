package hr.tis.academy.sightseeingapp.mapper;

import hr.tis.academy.sightseeingapp.dto.LocationDto;
import hr.tis.academy.sightseeingapp.model.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    LocationDto toDto(Location location);
    @Mapping(ignore = true, target = "id")
    Location toEntity(LocationDto dto);
}
