package com.github.bernabaris.userservice.controller;

import com.github.bernabaris.userservice.dto.LoginRequestDto;
import com.github.bernabaris.userservice.dto.LoginResponseDto;
import com.github.bernabaris.userservice.dto.UserDto;
import com.github.bernabaris.userservice.model.User;
import com.github.bernabaris.userservice.service.UserService;
import com.github.bernabaris.userservice.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
        try {
            User user = Converter.convertDtoToModel(userDto);
            User registeredUser = userService.registerUser(user);
            UserDto responseDto = new UserDto(registeredUser);
            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userService.getUserByUsername(username);
        return user.map(value -> ResponseEntity.ok(new UserDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.getUserByEmail(email);
        return user.map(value -> ResponseEntity.ok(new UserDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(value -> ResponseEntity.ok(new UserDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/exists/username/{username}")
    public ResponseEntity<Boolean> existsByUsername(@PathVariable String username) {
        Optional<User> user = userService.getUserByUsername(username);
        return user.map(value -> ResponseEntity.ok(user.isPresent())).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/exists/email/{email}")
    public ResponseEntity<Boolean> existsByEmail(@PathVariable String email) {
        Optional<User> user = userService.getUserByEmail(email);
        return user.map(value -> ResponseEntity.ok(user.isPresent())).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
        try {
            User user = userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(new LoginResponseDto("Login successful", user.getUsername()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
