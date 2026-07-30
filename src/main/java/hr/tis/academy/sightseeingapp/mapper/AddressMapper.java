package hr.tis.academy.sightseeingapp.mapper;

import hr.tis.academy.sightseeingapp.dto.AddressDto;
import hr.tis.academy.sightseeingapp.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDto toDto(Address address);

    @Mapping(ignore = true, target = "id")
    Address toEntity(AddressDto dto);
}
