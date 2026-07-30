package hr.tis.academy.sightseeingapp.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AddressDto(
        @Pattern(regexp = "^[A-Za-z]*$")
        @Size(min = 2, max = 2)
        String country,
        String city,
        String streetName,
        String houseNumber

) {
}
