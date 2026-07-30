package hr.tis.academy.sightseeingapp.controller;

import hr.tis.academy.sightseeingapp.dto.UserDto;
import hr.tis.academy.sightseeingapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "UserController", description = "User management")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "post")
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestParam(name = "name") String name, @RequestParam(name = "email") String email) {
         return userService.save(name, email);
    }
}
