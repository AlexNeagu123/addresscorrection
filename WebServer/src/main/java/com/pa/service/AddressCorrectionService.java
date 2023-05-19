package com.pa.service;

import com.pa.dto.AddressDTO;
import com.pa.entity.Address;
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
