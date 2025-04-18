package com.cankus.mapper;

import com.cankus.dto.UserDto;
import com.cankus.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;
    @Autowired  // Optional but can be added for clarity.
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // DTO -> Entity
    public User covertToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    // Entity -> DTO
    public UserDto covertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
