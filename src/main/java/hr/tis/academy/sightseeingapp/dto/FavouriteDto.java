package hr.tis.academy.sightseeingapp.dto;

public record FavouriteDto (
    String location,
    String attractionName,
    UserDto userDto
) {
}
