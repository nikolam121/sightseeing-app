package hr.tis.academy.sightseeingapp.controller;

import hr.tis.academy.sightseeingapp.dto.AttractionMetadataDto;
import hr.tis.academy.sightseeingapp.model.Location;
import hr.tis.academy.sightseeingapp.service.AttractionMetadataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/attractions")
public class AttractionMetadataController {
    private final AttractionMetadataService attractionMetadataService;

    public AttractionMetadataController(AttractionMetadataService attractionMetadataService) {
        this.attractionMetadataService = attractionMetadataService;
    }

    @GetMapping("/{location}")
    public ResponseEntity<List<AttractionMetadataDto>> listAttractions(@PathVariable("location") String location, Model model) {
        return ResponseEntity.ok(attractionMetadataService.findByLocation(location));
    }
}

