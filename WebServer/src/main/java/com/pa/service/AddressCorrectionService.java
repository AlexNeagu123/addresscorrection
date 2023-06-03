package com.pa.service;

import com.google.common.collect.Multimap;
import com.pa.entity.Address;
import com.pa.entity.Branch;
import com.pa.entity.FieldToken;
import com.pa.entity.GeoNode;
import com.pa.mapper.BranchMapper;
import com.pa.utility.AddressNormalizer;
import com.pa.utility.CandidateScorer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressCorrectionService {
    private final CandidateGeneratorService candidateGenerator;
    private final Multimap<GeoNode, String> nodeToAlternativeMap;
    private final BranchMapper branchMapper;

    public Address correctAddress(Address address) {
        List<FieldToken> fieldTokens = AddressNormalizer.normalizeAddress(address);
        List<Branch> candidateBranches = candidateGenerator.generateCandidateAddresses(fieldTokens);
        HashSet<FieldToken> compoundTokensSet = new HashSet<>(AddressNormalizer.getCompoundTokens(fieldTokens));
        System.out.println("All field tokens: " + compoundTokensSet);
        CandidateScorer candidateScorer = new CandidateScorer(compoundTokensSet, candidateBranches, nodeToAlternativeMap);
        return branchMapper.mapToAddress(candidateScorer.getBestCandidate());
    }
}
