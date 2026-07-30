package hr.tis.academy.sightseeingapp.mapper;

import hr.tis.academy.sightseeingapp.dto.FavouriteDto;
import hr.tis.academy.sightseeingapp.model.Favourite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, LocationMapper.class})
public interface FavouriteMapper {
    @Mapping(source = "attraction.name", target = "attractionName")
    @Mapping(source = "location.name", target = "location")
    FavouriteDto toDto(Favourite favourite);

    @Mapping(ignore = true, target = "id")
    @Mapping(source = "attractionName", target = "attraction.name")
    @Mapping(ignore = true, target = "user")
    @Mapping(source = "location", target = "location.name")
    Favourite toEntity(FavouriteDto dto);

    @Mapping(source = "attraction.name", target = "attractionName")
    List<FavouriteDto> toDto(List<Favourite> favourites);
}
