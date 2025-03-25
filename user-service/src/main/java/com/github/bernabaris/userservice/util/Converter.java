package com.github.bernabaris.userservice.util;

import com.github.bernabaris.userservice.dto.UserDto;
import com.github.bernabaris.userservice.entity.UserEntity;
import com.github.bernabaris.userservice.model.User;

public class Converter {
    public static UserEntity convertUserToUserEntity(User user) {
        UserEntity userEntity = new UserEntity();
        if( user.getId() != null ) {
            userEntity.setId(user.getId());
        }
        if( user.getEmail() != null ) {
            userEntity.setEmail(user.getEmail());
        }
        if( user.getPassword() != null ) {
            userEntity.setPassword(user.getPassword());
        }
        if( user.getRole() != null ) {
            userEntity.setRole(user.getRole());
        }
        return userEntity;
    }

    public static User convertUserEntityToUser(UserEntity userEntity) {
        User user = new User();

        if (userEntity.getId() != null) {
            user.setId(userEntity.getId());
        }
        if (userEntity.getEmail() != null) {
            user.setEmail(userEntity.getEmail());
        }
        if (userEntity.getUsername() != null) {
            user.setUsername(userEntity.getUsername());
        }
        if (userEntity.getPassword() != null) {
            user.setPassword(userEntity.getPassword());
        }
        if (userEntity.getRole() != null) {
            user.setRole(userEntity.getRole());
        }

        return user;
    }

    public static User convertDtoToModel(UserDto userDto) {
        User user = new User();
        if (userDto.getId() != null) {
            user.setId(userDto.getId());
        }
        if (userDto.getUsername() != null) {
            user.setUsername(userDto.getUsername());
        }
        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getPassword() != null) {
            user.setPassword(userDto.getPassword());
        }
        if (userDto.getRole() != null) {
            user.setRole(userDto.getRole());
        }
        return user;
    }

    public static UserDto convertUserToDto(User user) {
        UserDto userDto = new UserDto();
        if (user.getId() != null) {
            userDto.setId(user.getId());
        }
        if (user.getUsername() != null) {
            userDto.setUsername(user.getUsername());
        }
        if (user.getEmail() != null) {
            userDto.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            userDto.setPassword(user.getPassword());
        }
        if (user.getRole() != null) {
            userDto.setRole(user.getRole());
        }
        return userDto;
    }

}
