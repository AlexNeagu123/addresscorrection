package com.pa.controller;

import com.pa.entity.Address;
import com.pa.service.AddressCorrectionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/correction")
public class AddressCorrectionController {
    private final AddressCorrectionService addressCorrectionService;

    @PostMapping
    @Operation(tags = {"AddressCorrection"})
    public Address correctAddress(@RequestBody @Valid Address address) {
        return addressCorrectionService.correctAddress(address);
    }
}
