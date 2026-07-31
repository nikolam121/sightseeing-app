package hr.tis.academy.sightseeingapp.service.impl;

import hr.tis.academy.sightseeingapp.dto.AddressDto;
import hr.tis.academy.sightseeingapp.dto.FavouriteDto;
import hr.tis.academy.sightseeingapp.dto.HttpErrorDto;
import hr.tis.academy.sightseeingapp.dto.UserDto;
import hr.tis.academy.sightseeingapp.mapper.FavouriteMapper;
import hr.tis.academy.sightseeingapp.mapper.HttpErrorMapper;
import hr.tis.academy.sightseeingapp.mapper.LocationMapper;
import hr.tis.academy.sightseeingapp.mapper.UserMapper;
import hr.tis.academy.sightseeingapp.model.HttpError;
import hr.tis.academy.sightseeingapp.model.User;
import hr.tis.academy.sightseeingapp.repository.FavouriteRepository;
import hr.tis.academy.sightseeingapp.repository.HttpErrorRepository;
import hr.tis.academy.sightseeingapp.repository.UserRepository;
import hr.tis.academy.sightseeingapp.service.UserService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.boot.micrometer.observation.autoconfigure.ObservationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

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
        if (name == null) {
            UUID uuid = UUID.randomUUID();
            LocalDateTime localDateTime = LocalDateTime.now();
            String message = "Name cannot be empty.";
            HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

            HttpHeaders headers = new HttpHeaders();
            headers.set("message", message);
            headers.set("timestamp", localDateTime.toString());
            headers.set("uuid",  uuid.toString());

            httpErrorRepository.save(httpErrorMapper.toEntity(new HttpErrorDto(uuid, localDateTime, message, httpStatus, "POST")));
            return ResponseEntity.status(httpStatus).headers(headers).body(null);
        }

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);

        if (email == null || !pattern.matcher(email).matches()) {
            UUID uuid = UUID.randomUUID();
            LocalDateTime localDateTime = LocalDateTime.now();
            String message = "Email is empty or of wrong format.";
            HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

            HttpHeaders headers = new HttpHeaders();
            headers.set("message", message);
            headers.set("timestamp", localDateTime.toString());
            headers.set("uuid",  uuid.toString());

            httpErrorRepository.save(httpErrorMapper.toEntity(new HttpErrorDto(uuid, localDateTime, message, httpStatus, "POST")));

            return ResponseEntity.status(httpStatus).headers(headers).body(null);
        }

        String countryCodeRegex = "^[A-Z]{2}$";
        Pattern countryPattern = Pattern.compile(countryCodeRegex);
        if (!countryPattern.matcher(country).matches()) {
            UUID uuid = UUID.randomUUID();
            LocalDateTime localDateTime = LocalDateTime.now();
            String message = "Country code is of the wrong format.";
            HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

            HttpHeaders headers = new HttpHeaders();
            headers.set("message", message);
            headers.set("timestamp", localDateTime.toString());
            headers.set("uuid",  uuid.toString());

            httpErrorRepository.save(httpErrorMapper.toEntity(new HttpErrorDto(uuid, localDateTime, message, httpStatus, "POST")));
            return ResponseEntity.status(httpStatus).headers(headers).body(null);
        }


        if (!userRepository.existsByEmail(email)) {
            UserDto userDto = new UserDto(name, email, phoneNumber, dateOfBirth, new AddressDto(country, city, streetName, houseNumber));
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
                UUID uuid = UUID.randomUUID();
                LocalDateTime localDateTime = LocalDateTime.now();
                String message = "Wrong email format.";
                HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

                HttpHeaders headers = new HttpHeaders();
                headers.set("message", message);
                headers.set("timestamp", localDateTime.toString());
                headers.set("uuid",  uuid.toString());

                httpErrorRepository.save(httpErrorMapper.toEntity(new HttpErrorDto(uuid, localDateTime, message, httpStatus, "POST")));
                return ResponseEntity.status(httpStatus).headers(headers).body(null);
            }

        } else {
            UUID uuid = UUID.randomUUID();
            LocalDateTime localDateTime = LocalDateTime.now();
            String message = "User with this email already exists.";
            HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

            HttpHeaders headers = new HttpHeaders();
            headers.set("message", message);
            headers.set("timestamp", localDateTime.toString());
            headers.set("uuid",  uuid.toString());

            httpErrorRepository.save(httpErrorMapper.toEntity(new HttpErrorDto(uuid, localDateTime, message, httpStatus, "POST")));
            return ResponseEntity.status(httpStatus).headers(headers).body(null);
        }
    }

    @Override
    public ResponseEntity<UserDto> getById(Long userId) {
        if (!userRepository.existsById(userId)) {
            UUID uuid = UUID.randomUUID();
            LocalDateTime localDateTime = LocalDateTime.now();
            String message = "User not found.";
            HttpStatus httpStatus = HttpStatus.NOT_FOUND;

            HttpHeaders headers = new HttpHeaders();
            headers.set("message", message);
            headers.set("timestamp", localDateTime.toString());
            headers.set("uuid",  uuid.toString());

            httpErrorRepository.save(httpErrorMapper.toEntity(new HttpErrorDto(uuid, localDateTime, message, httpStatus, "POST")));
            return ResponseEntity.status(httpStatus).headers(headers).body(null);
        }

        User user = userRepository.getById(userId);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @Override
    public ResponseEntity<List<FavouriteDto>> getFavouritesByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            UUID uuid = UUID.randomUUID();
            LocalDateTime localDateTime = LocalDateTime.now();
            String message = "User not found: " + userId;
            HttpStatus httpStatus = HttpStatus.NOT_FOUND;

            HttpHeaders headers = new HttpHeaders();
            headers.set("message", message);
            headers.set("timestamp", localDateTime.toString());
            headers.set("uuid",  uuid.toString());

            httpErrorRepository.save(httpErrorMapper.toEntity(new HttpErrorDto(uuid, localDateTime, message, httpStatus, "POST")));;
            return ResponseEntity.status(httpStatus).headers(headers).body(null);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("timestamp", LocalTime.now().toString());
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(favouriteMapper.toDto(favouriteRepository.findAllByUserId(userId)));
    }
}
