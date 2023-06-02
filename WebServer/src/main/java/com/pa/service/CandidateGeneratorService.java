package com.pa.service;

import com.google.common.collect.Multimap;
import com.pa.entity.Address;
import com.pa.entity.GeoNode;
import com.pa.utility.AddressNormalizer;
import com.pa.utility.FieldToken;
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

    public List<Address> generateCandidateAddresses(List<FieldToken> fieldTokens) {
        List<String> s = fieldTokens.stream().map(FieldToken::getToken).distinct().toList();
        for(String tok : s) {
            List<Address> branches = new ArrayList<>();
            branches.addAll(findAllBranches(tok));
            System.out.println(tok + " " + branches);
        }
        return fieldTokens.stream()
                .map(FieldToken::getToken)
                .distinct()
                .map(this::findAllBranches)
                .flatMap(List::stream)
                .toList();
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
