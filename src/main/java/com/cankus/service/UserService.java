package com.cankus.service;

import com.cankus.dto.UserDto;

import java.util.List;

public interface UserService {

    // Tüm kullanıcıları getir
    List<UserDto> findAll();

}
