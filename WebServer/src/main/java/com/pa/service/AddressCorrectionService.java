package com.pa.service;

import com.pa.entity.Address;
import com.pa.utility.AddressNormalizer;
import com.pa.utility.CandidateGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressCorrectionService {
    private final CandidateGenerator candidateGenerator;

    public List<Address> correctAddress(Address address) {
        String[] addressTokens = AddressNormalizer.normalizeAddress(address);
        for(String add : addressTokens) {
            System.out.println(add);
        }
        List<Address> candidateAddresses = candidateGenerator.generateCandidateAddresses(addressTokens);
        return candidateAddresses;
    }
}
