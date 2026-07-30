package hr.tis.academy.sightseeingapp.service;

import hr.tis.academy.sightseeingapp.dto.FavouriteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Component
public interface FavouriteService {
    ResponseEntity<FavouriteDto> save(Long userID, String location, String attractionName);
}
