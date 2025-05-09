package com.cankus.service.implementation;

import com.cankus.dto.RoleDto;
import com.cankus.entity.Role;
import com.cankus.mapper.RoleMapper;
import com.cankus.repository.RoleRepository;
import com.cankus.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class RoleServiceImplementation implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImplementation(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }


    @Override
    public List<RoleDto> findAll() {
        return roleRepository.findAll().stream()
                .map(roleMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public RoleDto findById(Long id) {
        Role roleInDB = roleRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Role could not be found."));
        return roleMapper.convertToDto(roleInDB);
    }


}
