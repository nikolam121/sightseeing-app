package hr.tis.academy.sightseeingapp.service.impl;

import hr.tis.academy.sightseeingapp.dto.AddressDto;
import hr.tis.academy.sightseeingapp.dto.FavouriteDto;
import hr.tis.academy.sightseeingapp.dto.HttpErrorDto;
import hr.tis.academy.sightseeingapp.dto.UserDto;
import hr.tis.academy.sightseeingapp.mapper.FavouriteMapper;
import hr.tis.academy.sightseeingapp.mapper.HttpErrorMapper;
import hr.tis.academy.sightseeingapp.mapper.LocationMapper;
import hr.tis.academy.sightseeingapp.mapper.UserMapper;
import hr.tis.academy.sightseeingapp.model.User;
import hr.tis.academy.sightseeingapp.repository.FavouriteRepository;
import hr.tis.academy.sightseeingapp.repository.HttpErrorRepository;
import hr.tis.academy.sightseeingapp.repository.UserRepository;
import hr.tis.academy.sightseeingapp.service.UserService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$",
            Pattern.CASE_INSENSITIVE);
    private static final Pattern COUNTRY_CODE_PATTERN = Pattern.compile("^[A-Z]{2}$");

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final FavouriteRepository favouriteRepository;
    private final LocationMapper locationMapper;
    private final FavouriteMapper favouriteMapper;
    private final HttpErrorRepository httpErrorRepository;
    private final HttpErrorMapper httpErrorMapper;

    public UserServiceImpl(UserMapper userMapper,
                           UserRepository userRepository,
                           FavouriteRepository favouriteRepository,
                           LocationMapper locationMapper,
                           FavouriteMapper favouriteMapper,
                           HttpErrorRepository httpErrorRepository,
                           HttpErrorMapper httpErrorMapper) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.favouriteRepository = favouriteRepository;
        this.locationMapper = locationMapper;
        this.favouriteMapper = favouriteMapper;
        this.httpErrorRepository = httpErrorRepository;
        this.httpErrorMapper = httpErrorMapper;
    }

    private <T> ResponseEntity<T> logAndBuildError(String message, HttpStatus httpStatus, String httpMethod) {
        UUID uuid = UUID.randomUUID();
        LocalDateTime localDateTime = LocalDateTime.now();

        HttpHeaders headers = new HttpHeaders();
        headers.set("message", message);
        headers.set("timestamp", localDateTime.toString());
        headers.set("uuid", uuid.toString());

        httpErrorRepository.save(httpErrorMapper.toEntity(
                new HttpErrorDto(uuid, localDateTime, message, httpStatus, httpMethod)));

        return ResponseEntity.status(httpStatus).headers(headers).body(null);
    }

    @Override
    @Transactional
    public ResponseEntity<UserDto> save(String name,
                                        String email,
                                        String phoneNumber,
                                        LocalDate dateOfBirth,
                                        String country,
                                        String city,
                                        String streetName,
                                        String houseNumber) {
        if (name == null || name.isBlank()) {
            return logAndBuildError("Name cannot be empty.", HttpStatus.BAD_REQUEST, "POST");
        }

        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            return logAndBuildError("Email is empty or of wrong format.", HttpStatus.BAD_REQUEST, "POST");
        }

        if (country == null || !COUNTRY_CODE_PATTERN.matcher(country).matches()) {
            return logAndBuildError("Country code is of the wrong format.", HttpStatus.BAD_REQUEST, "POST");
        }

        if (userRepository.existsByEmail(email)) {
            return logAndBuildError("User with this email already exists.", HttpStatus.BAD_REQUEST, "POST");
        }

        UserDto userDto = new UserDto(name, email, phoneNumber, dateOfBirth,
                new AddressDto(country, city, streetName, houseNumber));
        User userEntity = userMapper.toEntity(userDto);

        try {
            User savedUser = userRepository.save(userEntity);

            HttpHeaders headers = new HttpHeaders();
            headers.set("message", "User has been saved successfully.");
            headers.set("timestamp", LocalTime.now().toString());
            headers.set("Location", "/user/" + savedUser.getId());

            return ResponseEntity.status(HttpStatus.CREATED)
                    .headers(headers)
                    .body(userMapper.toDto(savedUser));
        } catch (ConstraintViolationException e) {
            return logAndBuildError("Wrong email format.", HttpStatus.BAD_REQUEST, "POST");
        }
    }

    @Override
    public ResponseEntity<UserDto> getById(Long userId) {
        if (!userRepository.existsById(userId)) {
            return logAndBuildError("User not found.", HttpStatus.NOT_FOUND, "GET");
        }

        User user = userRepository.getById(userId);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @Override
    public ResponseEntity<List<FavouriteDto>> getFavouritesByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            return logAndBuildError("User not found: " + userId, HttpStatus.NOT_FOUND, "GET");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("timestamp", LocalTime.now().toString());
        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(favouriteMapper.toDto(favouriteRepository.findAllByUserId(userId)));
    }
}
