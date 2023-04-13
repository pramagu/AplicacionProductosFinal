package com.openwebinars.rest.Converter;

import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

import com.openwebinars.rest.Model.UserEntity;
import com.openwebinars.rest.Model.UserRole;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDTOConverter {

    public GetUserDTO convertToDto(UserEntity user) {
        return GetUserDTO.builder()
                .username(user.getUsername())
                .avatar(user.getAvatar())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .roles(user.getRoles().stream()
                        .map(UserRole::name)
                        .collect(Collectors.toSet()))
                .build();
    }

    public GetUserDTO convertUserEntityToGetUserDto(UserEntity user) {
        return GetUserDTO.builder()
                .username(user.getUsername())
                .avatar(user.getAvatar())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .roles(user.getRoles().stream()
                        .map(UserRole::name)
                        .collect(Collectors.toSet()))
                .build();
    }

}
