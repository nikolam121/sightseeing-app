package hr.tis.academy.sightseeingapp.mapper;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewDto toDto(Review review);
    List<ReviewDto> toDtoList(List<Review> reviews);
}
