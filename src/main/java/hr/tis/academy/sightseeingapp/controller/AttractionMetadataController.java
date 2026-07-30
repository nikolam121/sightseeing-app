package hr.tis.academy.sightseeingapp.controller;

import hr.tis.academy.sightseeingapp.dto.AttractionDetailsDto;
import hr.tis.academy.sightseeingapp.dto.AttractionMetadataDto;
import hr.tis.academy.sightseeingapp.model.AttractionMetadata;
import hr.tis.academy.sightseeingapp.model.Location;
import hr.tis.academy.sightseeingapp.service.AttractionDetailsService;
import hr.tis.academy.sightseeingapp.service.AttractionMetadataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Tag(name = "AttractionMetadataController", description = "AttractionMetadata management")
@RequestMapping("/attractions")
public class AttractionMetadataController {
    @Autowired
    private AttractionMetadataService attractionMetadataService;

    @Autowired
    private AttractionDetailsService attractionDetailsService;

    public AttractionMetadataController(AttractionMetadataService attractionMetadataService, AttractionDetailsService attractionDetailsService) {
        this.attractionMetadataService = attractionMetadataService;
        this.attractionDetailsService = attractionDetailsService;
    }

    @Operation(summary = "get")
    @GetMapping("/{location}")
    public ResponseEntity<AttractionMetadataDto> listAttractions(@PathVariable("location") String location, Model model) {
        return ResponseEntity.ok(attractionMetadataService.findByLocation(location));
    }

    @Operation(summary = "post")
    @PostMapping
    public ResponseEntity<AttractionMetadataDto> createAttractionMetadata(@RequestBody AttractionMetadataDto attractionMetadataDto) {
        AttractionMetadataDto savedAttractionMetadataDto = attractionMetadataService.save(attractionMetadataDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAttractionMetadataDto);
    }

    //TODO: excludeReviews, reviewsFrom, reviewsTo
    @Operation(summary = "get")
    @GetMapping("/{location}/{attractionURLName}")
    public ResponseEntity<AttractionDetailsDto> attractionDetails(@PathVariable("location") String location,
                                                                  @PathVariable("attractionURLName") String attractionURLName,
                                                                  @RequestParam(required = false) boolean excludeReviews,
                                                                  @RequestParam(required = false) LocalDateTime reviewsFrom,
                                                                  @RequestParam(required = false) LocalDateTime reviewsTo) {
        return attractionDetailsService.getAttractionDetailsByLocationAndURLName(location, attractionURLName, excludeReviews, reviewsFrom, reviewsTo);
    }
}

