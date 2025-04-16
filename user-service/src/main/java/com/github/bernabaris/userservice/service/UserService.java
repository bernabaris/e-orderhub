package com.github.bernabaris.userservice.service;

import com.github.bernabaris.userservice.entity.UserEntity;
import com.github.bernabaris.userservice.model.User;
import com.github.bernabaris.userservice.repository.UserRepository;
import com.github.bernabaris.userservice.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        if (userRepository.existsByUsername(user.getUsername()) || userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Username or email address already in use");
        }
        if(user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username is required");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity userEntity = Converter.convertUserToUserEntity(user);
        UserEntity savedUser = userRepository.save(userEntity);
        return Converter.convertUserEntityToUser(savedUser);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(user -> Converter.convertUserEntityToUser(user));
    }
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(user -> Converter.convertUserEntityToUser(user));
    }
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> Converter.convertUserEntityToUser(user));
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User loginUser(String email, String rawPassword) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);

        if (userEntityOptional.isEmpty()) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        UserEntity userEntity = userEntityOptional.get();

        if (!passwordEncoder.matches(rawPassword, userEntity.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        return Converter.convertUserEntityToUser(userEntity);
    }


}
