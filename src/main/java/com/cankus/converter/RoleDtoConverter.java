package com.cankus.converter;

import com.cankus.dto.RoleDto;
import com.cankus.service.RoleService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleDtoConverter implements Converter <String, RoleDto> {

    private final RoleService roleService;

    public RoleDtoConverter( RoleService roleService) {
        this.roleService = roleService;

    }

    @Override
    public RoleDto convert(String source) {
        return roleService.findById(Long.parseLong(source));
    }
}
