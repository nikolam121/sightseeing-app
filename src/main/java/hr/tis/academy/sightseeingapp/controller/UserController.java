package hr.tis.academy.sightseeingapp.controller;

import hr.tis.academy.sightseeingapp.dto.FavouriteDto;
import hr.tis.academy.sightseeingapp.dto.UserDto;
import hr.tis.academy.sightseeingapp.service.FavouriteService;
import hr.tis.academy.sightseeingapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "UserController", description = "User management")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FavouriteService favouriteService;

    public UserController(UserService userService,  FavouriteService favouriteService) {
        this.userService = userService;
        this.favouriteService = favouriteService;
    }

    @Operation(summary = "post")
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestParam(name = "name") String name,
                                              @RequestParam(name = "email") String email,
                                              @RequestParam(required = false, name = "phoneNumer") String phoneNumber,
                                              @RequestParam(required = false, name = "dateOfBirth") LocalDate dateOfBirth,
                                              @RequestParam(required = false, name = "country") String country,
                                              @RequestParam(required = false, name = "city") String city,
                                              @RequestParam(required = false, name = "streetName") String streetName,
                                              @RequestParam(required = false, name = "houseNumber") String houseNumber
                                              ) {
        return userService.save(name, email, phoneNumber, dateOfBirth, country, city, streetName, houseNumber);
    }

    @Operation(summary = "post")
    @PostMapping("/{userId}/favourites")
    public ResponseEntity<FavouriteDto> createFavourite(@PathVariable Long userId, @RequestParam(name = "location") String location, @RequestParam(name = "attractionName") String attractionName) {
        return favouriteService.save(userId, location, attractionName);
    }

    @Operation(summary = "get")
    @GetMapping("/{userId}/favourites")
    public ResponseEntity<List<FavouriteDto>> getFavouritesByUserId(@PathVariable Long userId) {
        List<FavouriteDto> favourites = userService.getFavouritesByUserId(userId);
        return ResponseEntity.ok(favourites);
    }

    @Operation(summary = "get")
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        return userService.getById(userId);
    }
}
