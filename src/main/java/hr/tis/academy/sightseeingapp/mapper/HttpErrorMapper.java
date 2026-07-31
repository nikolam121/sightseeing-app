package hr.tis.academy.sightseeingapp.mapper;

import hr.tis.academy.sightseeingapp.dto.HttpErrorDto;
import hr.tis.academy.sightseeingapp.model.HttpError;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HttpErrorMapper {
    HttpErrorDto toDto(HttpError httpError);
    HttpError toEntity(HttpErrorDto httpErrorDto);
}
