package com.pa.utility;

import com.google.common.collect.Multimap;
import com.pa.entity.Branch;
import com.pa.entity.FieldToken;
import com.pa.entity.GeoNode;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * The <tt>CandidateScorer</tt> class contains useful methods for computing the score of given a list of
 * candidate {@link Branch} objects and the list of all the compound {@link FieldToken} formed from the
 * input {@link com.pa.entity.Address} received by the API
 *
 * @author Alex Neagu
 * @author Cristian Fiodorov
 */
@Log4j2
public class CandidateScorer {
    private final Set<FieldToken> fieldTokens;
    private final List<Branch> candidateBranches;
    private final Multimap<GeoNode, String> nodeToAlternativeMap;
    private Branch bestCandidate;

    public CandidateScorer(Set<FieldToken> fieldTokens, List<Branch> candidateBranches, Multimap<GeoNode, String> nodeToAlternativeMap) {
        this.fieldTokens = fieldTokens;
        this.candidateBranches = candidateBranches;
        this.nodeToAlternativeMap = nodeToAlternativeMap;
        this.bestCandidate = null;
    }

    /**
     * @return The best candidate found after assigning scores to each {@link Branch}
     */
    public Branch getBestCandidate() {
        if (bestCandidate == null) {
            computeBestCandidate();
        }
        System.out.println("====================================");
        return bestCandidate;
    }

    private void computeBestCandidate() {
        this.bestCandidate = candidateBranches.get(0);
        double bestScore = 0;
        for (Branch candidateBranch : candidateBranches) {
            double score = computeScore(candidateBranch);
            System.out.println("Candidate: " + candidateBranch + " Score: " + score);
            if (score > bestScore) {
                bestScore = score;
                this.bestCandidate = candidateBranch;
            }
        }
    }

    /**
     * Given a candidate solution represented by a {@link Branch} object, computes the total score of this particular candidate
     * <p>
     * All the ascii names from the branch are analyzed, one by one, and compared to the tokens found in the compound list of {@link FieldToken}
     * objects computed after the normalization process
     *
     * @param candidateBranch A candidate solution {@link Branch}
     * @return The score assigned to this branch
     */
    private double computeScore(Branch candidateBranch) {
        return getScoreFromNode(candidateBranch.getCityNode(), 2, ScoringConstants.CITY_EXACT_MATCH, ScoringConstants.CITY_ALTERNATE_MATCH)
                + getScoreFromNode(candidateBranch.getStateNode(), 1, ScoringConstants.STATE_EXACT_MATCH, ScoringConstants.STATE_ALTERNATE_MATCH)
                + getScoreFromNode(candidateBranch.getCountryNode(), 0, ScoringConstants.COUNTRY_EXACT_MATCH, ScoringConstants.COUNTRY_ALTERNATE_MATCH);
    }

    private double getScoreFromNode(GeoNode currentNode, Integer currentFieldId, ScoringConstants exactMatchEnum, ScoringConstants alternateMatchEnum) {
        double score = 0;
        for (FieldToken fieldToken : fieldTokens) {
            if (fieldToken.getToken().equalsIgnoreCase(currentNode.getAsciiName().toLowerCase())) {
                score += exactMatchEnum.getScore();
            } else if (nodeToAlternativeMap.containsKey(currentNode) && nodeToAlternativeMap.get(currentNode).contains(fieldToken.getToken())) {
                score += alternateMatchEnum.getScore();
            } else {
                continue;
            }
            if (Objects.equals(fieldToken.getFieldId(), currentFieldId)) {
                score += ScoringConstants.POSITION_MATCH.getScore();
            }
        }
        System.out.print("Field: " + currentNode.getAsciiName() + " Score: " + score + " ");
        return score;
    }
}

