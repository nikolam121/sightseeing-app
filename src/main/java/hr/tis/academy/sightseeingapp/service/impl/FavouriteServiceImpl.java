package hr.tis.academy.sightseeingapp.service.impl;

import hr.tis.academy.sightseeingapp.dto.FavouriteDto;
import hr.tis.academy.sightseeingapp.mapper.FavouriteMapper;
import hr.tis.academy.sightseeingapp.mapper.UserMapper;
import hr.tis.academy.sightseeingapp.model.Favourite;
import hr.tis.academy.sightseeingapp.repository.FavouriteRepository;
import hr.tis.academy.sightseeingapp.repository.LocationRepository;
import hr.tis.academy.sightseeingapp.repository.UserRepository;
import hr.tis.academy.sightseeingapp.service.FavouriteService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.UUID;

@Service
public class FavouriteServiceImpl implements FavouriteService {

    private FavouriteMapper favouriteMapper;
    private FavouriteRepository favouriteRepository;
    private LocationRepository locationRepository;
    private UserRepository userRepository;
    private UserMapper userMapper;

    public FavouriteServiceImpl(FavouriteMapper favouriteMapper, FavouriteRepository favouriteRepository, LocationRepository locationRepository, UserRepository userRepository, UserMapper userMapper) {
        this.favouriteMapper = favouriteMapper;
        this.favouriteRepository = favouriteRepository;
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    @Transactional
    public ResponseEntity<FavouriteDto> save(UUID userId, String location, String attractionName) {
        if (!locationRepository.existsByName(location)) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("message", "Location does not exist.");
            headers.add("timestamp", LocalTime.now().toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(null);
        }

        if (!userRepository.existsById(userId)) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("message", "User does not exist.");
            headers.add("timestamp", LocalTime.now().toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(null);
        }

        if (favouriteRepository.existsByLocationAndAttractionName(location, attractionName)) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("message", "Attraction already exists as a favourite.");
            headers.add("timestamp", LocalTime.now().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {
            FavouriteDto favouriteDto = new FavouriteDto(location, attractionName, userMapper.toDto(userRepository.getById(userId)));
            Favourite favourite = favouriteMapper.toEntity(favouriteDto);
            Favourite savedFavourite = favouriteRepository.save(favourite);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Location", "/user/" + userId.toString() + "/favourites");
            return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(favouriteMapper.toDto(savedFavourite));
        }
    }
}
