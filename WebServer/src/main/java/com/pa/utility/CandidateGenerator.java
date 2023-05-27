package com.pa.utility;

import com.google.common.collect.Multimap;
import com.pa.entity.Address;
import com.pa.entity.GeoNode;
import com.pa.service.GeoGraphService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CandidateGenerator {
    private static final int WORD_LIMIT = 3;

    private final GeoGraphService geoGraphService;
    private final Multimap<String, GeoNode> nameToNodeMap;

    public List<Address> generateCandidateAddresses(String[] tokens) {
        List<Address> candidateAddresses = new ArrayList<>();
        for (int i = 0; i < tokens.length; ++i) {
            StringBuilder currentToken = new StringBuilder();
            for (int j = i; j < Math.min(i + WORD_LIMIT, tokens.length); ++j) {
                if (j > i) {
                    currentToken.append(" ");
                }
                currentToken.append(tokens[j]);
                candidateAddresses.addAll(findAllBranches(currentToken.toString()));
            }
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
