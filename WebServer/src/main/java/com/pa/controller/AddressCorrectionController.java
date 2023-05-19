package com.pa.controller;

import com.pa.dto.AddressDTO;
import com.pa.entity.Address;
import com.pa.service.AddressCorrectionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/correction")
public class AddressCorrectionController {
    private final AddressCorrectionService addressCorrectionService;

    @PostMapping
    @Operation(tags = {"AddressCorrection"})
    public Address correctAddress(@RequestBody @Valid AddressDTO addressDTO) {
        return addressCorrectionService.correctAddress(addressDTO);
    }
}
