package com.cankus.service.implementation;

import com.cankus.dto.AddressDto;
import com.cankus.entity.Address;
import com.cankus.mapper.AddressMapper;
import com.cankus.repository.AddressRepository;
import com.cankus.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
@Service
public class AddressServiceImplementation implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressServiceImplementation(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public AddressDto findById(Long id) {
        Address addressInDB = addressRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Address could not be found."));
        return addressMapper.convertToDto(addressInDB);
    }

    @Override
    public void delete(Long id) {
        Address addressInDB = addressRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Address could not be found."));
        addressInDB.setDeleted(true);
        addressRepository.save(addressInDB);
    }
}
