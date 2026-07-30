package hr.tis.academy.sightseeingapp.controller;

import hr.tis.academy.sightseeingapp.service.PictureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "PictureController", description = "Attraction picture management")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @Operation(summary = "post")
    @PostMapping("/attraction/{location}/{attractionURLName}/picture")
    public ResponseEntity<Void> addPicture(@PathVariable String location,
                                           @PathVariable String attractionURLName,
                                           @RequestHeader(value = HttpHeaders.CONTENT_TYPE, required = false) String contentType,
                                           @RequestBody(required = false) byte[] imageData) {
        return pictureService.addPicture(location, attractionURLName, contentType, imageData);
    }
}