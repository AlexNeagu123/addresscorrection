package com.pa.finalproject.service;

import com.pa.finalproject.dto.AddressDTO;
import com.pa.finalproject.entity.Address;
import org.springframework.stereotype.Service;

@Service
public class AddressCorrectionService {
    public Address correctAddress(AddressDTO addressDTO) {
        // code incoming
        return new Address(
                addressDTO.getAddress(),
                "",
                ""
        );
    }
}
