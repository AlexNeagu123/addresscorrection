package com.pa.utility;

import com.google.common.collect.Multimap;
import com.pa.entity.Address;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CandidateScorer {
    private final Set<FieldToken> fieldTokens;
    private final List<Address> candidateSolutions;
    private final Multimap<String, String> asciiToAlternativeMap;
    private Address bestCandidate;

    public CandidateScorer(Set<FieldToken> fieldTokens, List<Address> candidateSolutions, Multimap<String, String> asciiToAlternativeMap) {
        this.fieldTokens = fieldTokens;
        this.candidateSolutions = candidateSolutions;
        this.asciiToAlternativeMap = asciiToAlternativeMap;
        this.bestCandidate = null;
    }

    public Address getBestCandidate() {
        if (bestCandidate == null) {
            computeBestCandidate();
        }
        System.out.println("====================================");
        return bestCandidate;
    }

    private void computeBestCandidate() {
        this.bestCandidate = candidateSolutions.get(0);
        double bestScore = 0;
        for (Address candidateAddress : candidateSolutions) {
            double score = computeScore(candidateAddress);
            System.out.println("Candidate: " + candidateAddress + " Score: " + score);
            if (score > bestScore) {
                bestScore = score;
                this.bestCandidate = candidateAddress;
            }
        }
    }

    private double computeScore(Address candidateAddress) {
        return getScoreFromField(candidateAddress.getCity(), 2, ScoringConstants.CITY_EXACT_MATCH, ScoringConstants.CITY_ALTERNATE_MATCH)
                + getScoreFromField(candidateAddress.getState(), 1, ScoringConstants.STATE_EXACT_MATCH, ScoringConstants.STATE_ALTERNATE_MATCH)
                + getScoreFromField(candidateAddress.getCountry(), 0, ScoringConstants.COUNTRY_EXACT_MATCH, ScoringConstants.COUNTRY_ALTERNATE_MATCH);
    }

    private double getScoreFromField(String currentField, Integer currentFieldId, ScoringConstants exactMatchEnum, ScoringConstants alternateMatchEnum) {
        double score = 0;
        for (FieldToken fieldToken : fieldTokens) {
            if (fieldToken.getToken().equalsIgnoreCase(currentField.toLowerCase())) {
                score += exactMatchEnum.getScore();
            } else if (asciiToAlternativeMap.containsKey(currentField.toLowerCase()) && asciiToAlternativeMap.get(currentField.toLowerCase()).contains(fieldToken.getToken())) {
                score += alternateMatchEnum.getScore();
            } else {
                continue;
            }
            if (Objects.equals(fieldToken.getFieldId(), currentFieldId)) {
                score += ScoringConstants.POSITION_MATCH.getScore();
            }
        }
        System.out.print("Field: " + currentField + " Score: " + score + " ");
        return score;
    }
}

