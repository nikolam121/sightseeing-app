package hr.tis.academy.sightseeingapp.service.impl;

import hr.tis.academy.sightseeingapp.model.Attraction;
import hr.tis.academy.sightseeingapp.model.AttractionMetadata;
import hr.tis.academy.sightseeingapp.model.Picture;
import hr.tis.academy.sightseeingapp.repository.AttractionMetadataRepository;
import hr.tis.academy.sightseeingapp.repository.PictureRepository;
import hr.tis.academy.sightseeingapp.service.PictureService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.UUID;

@Service
public class PictureServiceImpl implements PictureService {

    private final AttractionMetadataRepository attractionMetadataRepository;
    private final PictureRepository pictureRepository;

    public PictureServiceImpl(AttractionMetadataRepository attractionMetadataRepository, PictureRepository pictureRepository) {
        this.attractionMetadataRepository = attractionMetadataRepository;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public ResponseEntity<Void> addPicture(String location, String attractionURLName, String contentType, byte[] imageData) {
        if (imageData == null || imageData.length == 0 || contentType == null || !contentType.startsWith("image/")) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("message", "Request body must contain an image");
            headers.set("timestamp", LocalTime.now().toString());
            headers.set("uuid", UUID.randomUUID().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).body(null);
        }

        AttractionMetadata metadata = attractionMetadataRepository.findByLocation(location);
        if (metadata == null) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("message", "Location not found: " + location);
            headers.set("timestamp", LocalTime.now().toString());
            headers.set("uuid", UUID.randomUUID().toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(null);
        }

        Attraction attraction = null;
        for (Attraction a : metadata.getAttractions()) {
            if (a.getUrlName().equals(attractionURLName)) {
                attraction = a;
                break;
            }
        }

        if (attraction == null) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("message", "Attraction not found: " + attractionURLName + " in " + location);
            headers.set("timestamp", LocalTime.now().toString());
            headers.set("uuid", UUID.randomUUID().toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(null);
        }

        Picture picture = new Picture();
        picture.setAttraction(attraction);
        picture.setContentType(contentType);
        picture.setData(imageData);

        Picture savedPicture = pictureRepository.save(picture);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Location", "/attraction/" + location + "/" + attractionURLName + "/picture/" + savedPicture.getId());
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(null);
    }

    @Override
    public ResponseEntity<byte[]> getPicture(String location, String attractionURLName, Long pictureId) {
        AttractionMetadata metadata = attractionMetadataRepository.findByLocation(location);
        if (metadata == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Attraction attraction = null;
        for (Attraction a : metadata.getAttractions()) {
            if (a.getUrlName().equals(attractionURLName)) {
                attraction = a;
                break;
            }
        }

        if (attraction == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Picture picture = pictureRepository.findById(pictureId).orElse(null);
        if (picture == null || !picture.getAttraction().getId().equals(attraction.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(picture.getContentType()));

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(picture.getData());
    }
}