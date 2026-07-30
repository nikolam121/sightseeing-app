package hr.tis.academy.sightseeingapp.mapper;

import hr.tis.academy.sightseeingapp.dto.FavouriteDto;
import hr.tis.academy.sightseeingapp.model.Favourite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, LocationMapper.class})
public interface FavouriteMapper {
    @Mapping(source = "attraction.name", target = "attractionName")
    @Mapping(source = "user", target = "userDto")
    FavouriteDto toDto(Favourite favourite);

    @Mapping(ignore = true, target = "id")
    @Mapping(source = "attractionName", target = "attraction.name")
    @Mapping(source = "userDto", target = "user")
    Favourite toEntity(FavouriteDto dto);
}
