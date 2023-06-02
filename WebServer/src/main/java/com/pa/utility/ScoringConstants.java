package com.pa.utility;

public enum ScoringConstants {
    POSITION_MATCH(0.5),
    COUNTRY_ALTERNATE_MATCH(1),
    COUNTRY_EXACT_MATCH(2),
    STATE_ALTERNATE_MATCH(3),
    STATE_EXACT_MATCH(5),
    CITY_ALTERNATE_MATCH(6),
    CITY_EXACT_MATCH(9);

    private final double score;

    ScoringConstants(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }
}
