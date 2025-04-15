package com.cankus.mapper;

import com.cankus.dto.AddressDto;
import com.cankus.entity.Address;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    private final ModelMapper modelMapper;
    @Autowired
    public AddressMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    // DTO -> Entity
    public Address convertToEntity(AddressDto  addressDto){
        return modelMapper.map(addressDto, Address.class);
    }

    // Entity -> DTO
    public AddressDto convertToDto(Address address){
        return modelMapper.map(address, AddressDto.class);
    }
}
