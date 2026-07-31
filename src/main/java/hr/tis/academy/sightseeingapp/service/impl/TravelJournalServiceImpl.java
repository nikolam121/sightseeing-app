package hr.tis.academy.sightseeingapp.service.impl;

import hr.tis.academy.sightseeingapp.dto.AttractionJournalMetadataDto;
import hr.tis.academy.sightseeingapp.dto.TravelJournalDto;
import hr.tis.academy.sightseeingapp.mapper.TravelJournalMapper;
import hr.tis.academy.sightseeingapp.model.AttractionJournalMetadata;
import hr.tis.academy.sightseeingapp.model.TravelJournal;
import hr.tis.academy.sightseeingapp.repository.AttractionRepository;
import hr.tis.academy.sightseeingapp.repository.TravelJournalRepository;
import hr.tis.academy.sightseeingapp.repository.UserRepository;
import hr.tis.academy.sightseeingapp.service.TravelJournalService;
import hr.tis.academy.sightseeingapp.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class TravelJournalServiceImpl implements TravelJournalService {

    private final TravelJournalMapper travelJournalMapper;
    private final TravelJournalRepository travelJournalRepository;
    private final UserRepository userRepository;
    private final AttractionRepository attractionRepository;

    public TravelJournalServiceImpl(TravelJournalMapper travelJournalMapper,
                                    TravelJournalRepository travelJournalRepository,
                                    UserRepository userRepository,
                                    AttractionRepository attractionRepository) {
        this.travelJournalMapper = travelJournalMapper;
        this.travelJournalRepository = travelJournalRepository;
        this.userRepository = userRepository;
        this.attractionRepository = attractionRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<TravelJournalDto> save(Long userId, TravelJournalDto travelJournalDto) {
        if (!userRepository.existsById(userId)) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("message", "User not found: " + userId);
            headers.set("timestamp", LocalTime.now().toString());
            headers.set("uuid", UUID.randomUUID().toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(null);
        }

        boolean allExist = true;
        List<AttractionJournalMetadataDto> temp = travelJournalDto.attractions();
        for (AttractionJournalMetadataDto a : temp) {
            if (!attractionRepository.existsByName(a.attraction())) {
                allExist = false;
                break;
            }
        }

        if (!allExist) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("message", "Attraction does not exist.");
            headers.set("timestamp", LocalTime.now().toString());
            headers.set("uuid", UUID.randomUUID().toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(null);
        }

        if (travelJournalDto.description() == null || travelJournalDto.description().isEmpty() || travelJournalDto.startDate() == null) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("message", "All fields except endDate are mandatory.");
            headers.set("timestamp", LocalTime.now().toString());
            headers.set("uuid", UUID.randomUUID().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).body(null);
        }

        TravelJournal travelJournal = travelJournalMapper.toEntity(travelJournalDto);
        travelJournal.setUser(userRepository.getById(userId));
        TravelJournal savedTravelJournal = travelJournalRepository.save(travelJournal);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Location", "/travel-journal/" + savedTravelJournal.getId());
        headers.set("timestamp", LocalTime.now().toString());

        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(travelJournalMapper.toDto(savedTravelJournal));
    }

    @Override
    @Transactional
    public ResponseEntity<Void> modify(Long travelJournalId, TravelJournalDto patchDto) {
        if (!travelJournalRepository.existsById(travelJournalId)) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("message", "Travel journal does not exist: " + travelJournalId);
            headers.set("timestamp", LocalTime.now().toString());
            headers.set("uuid", UUID.randomUUID().toString());
            return ResponseEntity.notFound().headers(headers).build();
        } else {
            TravelJournal existingTravelJournal = travelJournalRepository.getById(travelJournalId);
            TravelJournalDto existingDto = travelJournalMapper.toDto(existingTravelJournal);


            TravelJournalDto newDto = new TravelJournalDto(
                    Objects.requireNonNullElse(patchDto.startDate(), existingDto.startDate()),
                    Objects.requireNonNullElse(patchDto.endDate(), existingDto.endDate()),
                    Objects.requireNonNullElse(patchDto.description(), existingDto.description()),
                    existingDto.attractions()
            );
            newDto.attractions().addAll(patchDto.attractions());

            TravelJournal newTravelJournal = travelJournalMapper.toEntity(newDto);
            newTravelJournal.setId(travelJournalId);
            travelJournalRepository.save(newTravelJournal);
            return ResponseEntity.ok().build();
        }
    }

    @Override
    public ResponseEntity<TravelJournalDto> getById(Long travelJournalId) {
        if (!travelJournalRepository.existsById(travelJournalId)) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("message", "Travel journal not found: " + travelJournalId);
            headers.set("timestamp", LocalTime.now().toString());
            headers.set("uuid", UUID.randomUUID().toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(null);
        }

        TravelJournal travelJournal = travelJournalRepository.getById(travelJournalId);
        return ResponseEntity.ok(travelJournalMapper.toDto(travelJournal));
    }
}
