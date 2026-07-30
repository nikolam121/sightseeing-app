package hr.tis.academy.sightseeingapp.mapper;

import hr.tis.academy.sightseeingapp.dto.FavouriteDto;
import hr.tis.academy.sightseeingapp.model.Favourite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface FavouriteMapper {
    @Mapping(source = "location.name", target = "location")
    @Mapping(source = "attraction.name", target = "attractionName")
    @Mapping(source = "user", target = "userDto")
    FavouriteDto toDto(Favourite favourite);

    //eventualno ne ic direktno na attraction.names nego stavit AttractionMapper.class u "uses" i probat cijelu klasu stavit kao target
    @Mapping(ignore = true, target = "id")
    @Mapping(source = "location", target = "location.name")
    @Mapping(source = "attractionName", target = "attraction.name")
    @Mapping(source = "userDto", target = "user")
    Favourite toEntity(FavouriteDto dto);
}
