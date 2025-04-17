package com.cankus.service;

import com.cankus.dto.UserDto;

import java.util.List;

public interface UserService {

    // Tüm kullanıcıları getir
    List<UserDto> findAll();

    boolean isEmailRegistered(String email);

    boolean isPasswordMatched(String password, String confirmPassword);

    void save(UserDto userDto);

    UserDto findById(Long id);

    void update(UserDto userDto);

    void delete(Long id);

    List<UserDto> getAllManagers();



}
