package hr.tis.academy.sightseeingapp.service;

import hr.tis.academy.sightseeingapp.dto.FavouriteDto;
import org.springframework.http.ResponseEntity;

public interface FavouriteService {
    ResponseEntity<FavouriteDto> save(Long userId, FavouriteDto favouriteDto);
}
