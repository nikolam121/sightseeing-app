package hr.tis.academy.sightseeingapp.service;

import org.springframework.http.ResponseEntity;

public interface PictureService {
    ResponseEntity<Void> addPicture(String location, String attractionURLName, String contentType, byte[] imageData);
    ResponseEntity<byte[]> getPicture(String location, String attractionURLName, Long pictureId);

}
