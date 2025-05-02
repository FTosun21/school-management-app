package com.cankus.converter;

import com.cankus.dto.RoleDto;
import com.cankus.service.RoleService;
import jakarta.annotation.Nullable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleDtoConverter implements Converter <String, RoleDto> {

    private final RoleService roleService;

    public RoleDtoConverter( RoleService roleService) {
        this.roleService = roleService;

    }

    @Override
    public RoleDto convert(@Nullable String source) {
        if (source == null || source.isBlank()) {
            return null;
        }
        return roleService.findById(Long.parseLong(source));
    }
}
