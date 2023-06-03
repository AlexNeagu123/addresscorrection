package com.pa.service;

import com.google.common.collect.Multimap;
import com.pa.entity.Address;
import com.pa.entity.Branch;
import com.pa.entity.GeoNode;
import com.pa.entity.FieldToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CandidateGeneratorService {
    private final GeoGraphService geoGraphService;
    private final Multimap<String, GeoNode> nameToNodeMap;

    public List<Branch> generateCandidateAddresses(Set<FieldToken> fieldTokens) {
        return fieldTokens.stream()
                .map(FieldToken::getToken)
                .distinct()
                .map(this::findAllBranches)
                .flatMap(List::stream)
                .toList();
    }

    private List<Branch> findAllBranches(String token) {
        List<Branch> allBranches = new ArrayList<>();
        for (GeoNode geoNode : nameToNodeMap.get(token)) {
            if (geoNode.getDepth() != 3) {
                continue;
            }
            allBranches.add(geoGraphService.getBranch(geoNode));
        }
        return allBranches;
    }
}
