package com.cankus.service;

import com.cankus.dto.AddressDto;

public interface AddressService {

    AddressDto findById(Long id);

    void delete(Long id);
}
