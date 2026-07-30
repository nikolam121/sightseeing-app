package hr.tis.academy.sightseeingapp.service.impl;

import hr.tis.academy.sightseeingapp.dto.FavouriteDto;
import hr.tis.academy.sightseeingapp.dto.UserDto;
import hr.tis.academy.sightseeingapp.mapper.UserMapper;
import hr.tis.academy.sightseeingapp.model.User;
import hr.tis.academy.sightseeingapp.repository.FavouriteRepository;
import hr.tis.academy.sightseeingapp.repository.UserRepository;
import hr.tis.academy.sightseeingapp.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;
    private UserRepository userRepository;
    private final FavouriteRepository favouriteRepository;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository, FavouriteRepository favouriteRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.favouriteRepository = favouriteRepository;
    }


    @Override
    @Transactional
    public ResponseEntity<UserDto> save(String name, String email) {
        if (name == null) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("message", "Name cannot be empty");
            headers.set("timestamp", LocalTime.now().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).body(null);
        }

        if (email == null) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("message", "Email cannot be empty");
            headers.set("timestamp", LocalTime.now().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).body(null);
        }


        if (!userRepository.existsByEmail(email)) {
            UserDto userDto = new UserDto(name, email);
            User userEntity = userMapper.toEntity(userDto);
            User savedUser = userRepository.save(userEntity);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Location", "/user/" + savedUser.getId());

            return ResponseEntity.status(HttpStatus.CREATED)
                    .headers(headers)
                    .body(userMapper.toDto(savedUser));

        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.set("message", "Users with this email already exists");
            headers.set("timestamp", LocalTime.now().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).body(null);
        }
    }

    @Override
    public List<FavouriteDto> getFavouritesByUserId(UUID userId) {
        return favouriteRepository.findAllByUserId(userId)
                .stream()
                .map(favourite -> new FavouriteDto(
                        favourite.getLocation().getName(),
                        favourite.getAttraction().getName(),
                        null
                ))
                .toList();
    }
}
