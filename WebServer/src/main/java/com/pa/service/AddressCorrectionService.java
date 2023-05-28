package com.pa.service;

import com.google.common.collect.Multimap;
import com.pa.entity.Address;
import com.pa.entity.GeoName;
import com.pa.entity.GeoNode;
import com.pa.utility.AddressNormalizer;
import com.pa.utility.CandidateScorer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressCorrectionService {
    private final CandidateGeneratorService candidateGenerator;
    private final Multimap<String, String> asciiToAlternativeMap;

    public Address correctAddress(Address address) {
        List<String> addressTokens = AddressNormalizer.normalizeAddress(address);
        System.out.println("Address tokens: " + addressTokens);
        List<Address> candidateAddresses = candidateGenerator.generateCandidateAddresses(addressTokens);
        HashSet<String> compoundTokensSet = new HashSet<>(AddressNormalizer.getCompoundTokens(addressTokens));

        CandidateScorer candidateScorer = new CandidateScorer(compoundTokensSet, candidateAddresses, asciiToAlternativeMap);
        return candidateScorer.getBestCandidate();
    }
}
