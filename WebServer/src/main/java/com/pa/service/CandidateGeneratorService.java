package com.pa.service;

import com.google.common.collect.Multimap;
import com.pa.entity.Address;
import com.pa.entity.GeoNode;
import com.pa.utility.AddressNormalizer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateGeneratorService {
    private final GeoGraphService geoGraphService;
    private final Multimap<String, GeoNode> nameToNodeMap;

    public List<Address> generateCandidateAddresses(List<String> tokens) {
        List<Address> candidateAddresses = new ArrayList<>();
        for (String currentToken : AddressNormalizer.getCompoundTokens(tokens)) {
            candidateAddresses.addAll(findAllBranches(currentToken));
        }
        return candidateAddresses;
    }

    private List<Address> findAllBranches(String token) {
        List<Address> allBranches = new ArrayList<>();
        for (GeoNode geoNode : nameToNodeMap.get(token)) {
            if (geoNode.getDepth() != 3) {
                continue;
            }
            allBranches.add(geoGraphService.getBranch(geoNode));
        }
        return allBranches;
    }
}
