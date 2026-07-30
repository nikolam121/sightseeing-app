package hr.tis.academy.sightseeingapp.dto;

public record FavouriteDto (
    LocationDto location,
    String attractionName
) {
}
