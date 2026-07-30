package hr.tis.academy.sightseeingapp.mapper;

import hr.tis.academy.sightseeingapp.dto.ReviewDto;
import hr.tis.academy.sightseeingapp.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    @Mapping(source = "attraction.name", target = "attractionName")
    @Mapping(source = "location.name", target = "location")
    ReviewDto toDto(Review review);

    @Mapping(ignore = true, target = "id")
    @Mapping(source = "location", target = "location.name")
    @Mapping(source = "attractionName", target = "attraction.name")
    Review toEntity(ReviewDto review);

    List<ReviewDto> toDto(List<Review> reviews);
}
