package hr.tis.academy.sightseeingapp.mapper;

import hr.tis.academy.sightseeingapp.dto.UserDto;
import hr.tis.academy.sightseeingapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface UserMapper {
    @Mapping(source = "address", target = "addressDto")
    UserDto toDto(User user);

    @Mapping(ignore = true, target = "id")
    @Mapping(source = "addressDto", target = "address")
    User toEntity(UserDto userDto);

}
