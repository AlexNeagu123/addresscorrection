package com.pa.utility;

import com.google.common.collect.Multimap;
import com.pa.entity.Address;

import java.util.List;
import java.util.Set;

public class CandidateScorer {
    private final Set<String> addressTokens;
    private final List<Address> candidateSolutions;
    private final Multimap<String, String> asciiToAlternativeMap;
    private Address bestCandidate;

    public CandidateScorer(Set<String> addressTokens, List<Address> candidateSolutions, Multimap<String, String> asciiToAlternativeMap) {
        this.addressTokens = addressTokens;
        this.candidateSolutions = candidateSolutions;
        this.asciiToAlternativeMap = asciiToAlternativeMap;
        this.bestCandidate = null;
    }

    public Address getBestCandidate() {
        if (bestCandidate == null) {
            computeBestCandidate();
        }
        return bestCandidate;
    }

    private void computeBestCandidate() {
        this.bestCandidate = candidateSolutions.get(0);
        int bestScore = 0;
        for (Address candidateAddress : candidateSolutions) {
            int score = computeScore(candidateAddress);
            if (score > bestScore) {
                bestScore = score;
                this.bestCandidate = candidateAddress;
            }
        }
    }

    private int computeScore(Address candidateAddress) {
        return getScoreFromField(candidateAddress.getCity(), ScoringConstants.CITY_EXACT_MATCH, ScoringConstants.CITY_ALTERNATE_MATCH)
                + getScoreFromField(candidateAddress.getState(), ScoringConstants.STATE_EXACT_MATCH, ScoringConstants.STATE_ALTERNATE_MATCH)
                + getScoreFromField(candidateAddress.getCountry(), ScoringConstants.COUNTRY_EXACT_MATCH, ScoringConstants.COUNTRY_ALTERNATE_MATCH);
    }

    private int getScoreFromField(String field, ScoringConstants exactMatchEnum, ScoringConstants alternateMatchEnum) {
        int score = 0;
        for (String token : addressTokens) {
            if (token.equalsIgnoreCase(field)) {
                score += exactMatchEnum.getScore();
            }
            if (asciiToAlternativeMap.containsKey(token) && asciiToAlternativeMap.get(token).contains(field)) {
                score += alternateMatchEnum.getScore();
            }
        }
        return score;
    }
}

