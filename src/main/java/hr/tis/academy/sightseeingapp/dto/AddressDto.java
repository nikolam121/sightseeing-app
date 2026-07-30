package hr.tis.academy.sightseeingapp.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AddressDto(
        String country,
        String city,
        String streetName,
        String houseNumber

) {
}
