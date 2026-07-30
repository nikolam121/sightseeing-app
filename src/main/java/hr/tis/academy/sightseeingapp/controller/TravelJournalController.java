package hr.tis.academy.sightseeingapp.controller;

import hr.tis.academy.sightseeingapp.dto.TravelJournalDto;
import hr.tis.academy.sightseeingapp.service.TravelJournalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Tag(name = "TravelJournalController", description = "Travel journal management")
@RequestMapping("/travel-journal")
public class TravelJournalController {
    @Autowired
    private TravelJournalService travelJournalService;

    public TravelJournalController(TravelJournalService travelJournalService) {
        this.travelJournalService = travelJournalService;
    }

    @Operation(summary = "post")
    @PostMapping("/{userId}")
    public ResponseEntity<TravelJournalDto> createTravelJournal(@PathVariable("userId") Long userId, @RequestBody TravelJournalDto travelJournalDto) {
        return travelJournalService.save(userId, travelJournalDto);
    }

    @Operation(summary = "patch")
    @PatchMapping("/{travelJournalId}")
    public ResponseEntity<Void> modify(@PathVariable("travelJournalId") Long travelJournalId, @RequestBody TravelJournalDto patchDto) {
        return travelJournalService.modify(travelJournalId, patchDto);
    }

    @Operation(summary = "get")
    @GetMapping("/{travelJournalId}")
    public ResponseEntity<TravelJournalDto> getById(@PathVariable("travelJournalId") Long travelJournalId) {
        return travelJournalService.getById(travelJournalId);
    }


}
