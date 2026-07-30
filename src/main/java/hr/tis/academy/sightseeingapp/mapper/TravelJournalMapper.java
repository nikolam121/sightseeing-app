package hr.tis.academy.sightseeingapp.mapper;

import hr.tis.academy.sightseeingapp.dto.TravelJournalDto;
import hr.tis.academy.sightseeingapp.model.TravelJournal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TravelJournalMapper {
    TravelJournalDto toDto(TravelJournal travelJournal);

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "user")
    TravelJournal toEntity(TravelJournalDto dto);
}
