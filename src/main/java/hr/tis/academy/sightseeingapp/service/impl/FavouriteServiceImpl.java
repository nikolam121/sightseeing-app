package hr.tis.academy.sightseeingapp.service.impl;

import hr.tis.academy.sightseeingapp.dto.FavouriteDto;
import hr.tis.academy.sightseeingapp.mapper.FavouriteMapper;
import hr.tis.academy.sightseeingapp.mapper.LocationMapper;
import hr.tis.academy.sightseeingapp.mapper.UserMapper;
import hr.tis.academy.sightseeingapp.model.Favourite;
import hr.tis.academy.sightseeingapp.repository.AttractionRepository;
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

    private final LocationMapper locationMapper;
    private final FavouriteMapper favouriteMapper;
    private final FavouriteRepository favouriteRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AttractionRepository attractionRepository;

    public FavouriteServiceImpl(FavouriteMapper favouriteMapper, FavouriteRepository favouriteRepository, LocationRepository locationRepository, UserRepository userRepository, UserMapper userMapper, LocationMapper locationMapper, AttractionRepository attractionRepository) {
        this.favouriteMapper = favouriteMapper;
        this.favouriteRepository = favouriteRepository;
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.locationMapper = locationMapper;
        this.attractionRepository = attractionRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<FavouriteDto> save(Long userId, FavouriteDto favouriteDto) {
        if (!locationRepository.existsByName(favouriteDto.location())) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("message", "Location does not exist.");
            headers.add("timestamp", LocalTime.now().toString());
            headers.add("uuid",  UUID.randomUUID().toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(null);
        }

        if (!userRepository.existsById(userId)) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("message", "User does not exist.");
            headers.add("timestamp", LocalTime.now().toString());
            headers.add("uuid",  UUID.randomUUID().toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(null);
        }

        if (!attractionRepository.existsByName(favouriteDto.attractionName())) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("message", "Attraction does not exist.");
            headers.add("timestamp", LocalTime.now().toString());
            headers.add("uuid",  UUID.randomUUID().toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(null);
        }

        if (favouriteRepository.existsByLocationAndAttractionName(locationRepository.getByName(favouriteDto.location()), favouriteDto.attractionName())) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("message", "Attraction already exists as a favourite.");
            headers.add("timestamp", LocalTime.now().toString());
            headers.add("uuid",  UUID.randomUUID().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        FavouriteDto newFavouriteDto = new FavouriteDto(locationMapper.toDto(locationRepository.getByName(favouriteDto.location())).name(), favouriteDto.attractionName());
        Favourite favourite = favouriteMapper.toEntity(newFavouriteDto);
        favourite.setUser(userRepository.getById(userId));
        Favourite savedFavourite = favouriteRepository.save(favourite);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Location", "/user/" + userId.toString() + "/favourites");
        headers.add("message", "Added new favourite.");
        headers.add("timestamp", LocalTime.now().toString());
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(favouriteMapper.toDto(savedFavourite));

    }
}
