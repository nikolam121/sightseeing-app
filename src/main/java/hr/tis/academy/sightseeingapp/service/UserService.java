package hr.tis.academy.sightseeingapp.service;

import hr.tis.academy.sightseeingapp.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    ResponseEntity<UserDto> save(String name, String email);
}
