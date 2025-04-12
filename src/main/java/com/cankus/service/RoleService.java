package com.cankus.service;

import com.cankus.dto.RoleDto;
import com.cankus.dto.UserDto;

import java.util.List;

public interface RoleService {

    // Tüm kullanıcıları getir
    List<RoleDto> findAll();
    //
    RoleDto findById(Long id);
}
