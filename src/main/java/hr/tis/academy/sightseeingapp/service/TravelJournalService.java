package hr.tis.academy.sightseeingapp.service;

import hr.tis.academy.sightseeingapp.dto.TravelJournalDto;
import org.springframework.http.ResponseEntity;

public interface TravelJournalService {
    ResponseEntity<TravelJournalDto> save(Long userId, TravelJournalDto travelJournalDto);
    ResponseEntity<Void> modify(Long travelJournalId, TravelJournalDto patchDto);
    ResponseEntity<TravelJournalDto> getById(Long travelJournalId);
}
