package hr.tis.academy.sightseeingapp.service;

import hr.tis.academy.sightseeingapp.dto.FavouriteDto;
import hr.tis.academy.sightseeingapp.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    ResponseEntity<UserDto> save(String name,
                                 String email,
                                 String phoneNumber,
                                 LocalDate dateOfBirth,
                                 String country,
                                 String city,
                                 String streetName,
                                 String houseNumber);
    ResponseEntity<List<FavouriteDto>> getFavouritesByUserId(Long userId);
    ResponseEntity<UserDto> getById(Long userId);
}
