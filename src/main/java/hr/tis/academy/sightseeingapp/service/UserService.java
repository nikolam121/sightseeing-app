package hr.tis.academy.sightseeingapp.service;

import hr.tis.academy.sightseeingapp.dto.FavouriteDto;
import hr.tis.academy.sightseeingapp.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface UserService {
    ResponseEntity<UserDto> save(String name, String email);
    List<FavouriteDto> getFavouritesByUserId(UUID userId);}
