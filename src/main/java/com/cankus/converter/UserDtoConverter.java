package com.cankus.converter;

import com.cankus.dto.UserDto;
import com.cankus.service.UserService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter implements Converter<String,UserDto> {

    private final UserService userService;

    public UserDtoConverter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDto convert(String source) {
        return userService.findById(Long.parseLong(source)) ;
    }
}
