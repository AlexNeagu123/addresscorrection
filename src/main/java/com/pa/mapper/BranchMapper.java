package com.pa.mapper;

import com.pa.entity.Address;
import com.pa.entity.Branch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BranchMapper {
    public static Address mapToAddress(Branch branch) {
        return Address.builder()
                .country(branch.getCountryNode().getAsciiName())
                .state(branch.getStateNode().getAsciiName())
                .city(branch.getCityNode().getAsciiName())
                .build();
    }
}