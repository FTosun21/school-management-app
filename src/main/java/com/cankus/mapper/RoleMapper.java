package com.cankus.mapper;

import com.cankus.dto.RoleDto;
import com.cankus.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    private final ModelMapper modelMapper;

    public RoleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    // DTO -> Entity
    public Role convertToEntity(RoleDto roleDto){
        return modelMapper.map(roleDto,Role.class);
    }

    // Entity -> DTO
    public RoleDto covertToDto(Role role){
        return modelMapper.map(role, RoleDto.class);
    }
}
