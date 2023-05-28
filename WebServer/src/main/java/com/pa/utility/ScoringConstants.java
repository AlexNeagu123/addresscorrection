package com.pa.utility;

public enum ScoringConstants {
    COUNTRY_ALTERNATE_MATCH(1),
    COUNTRY_EXACT_MATCH(2),
    STATE_ALTERNATE_MATCH(3),
    STATE_EXACT_MATCH(5),
    CITY_ALTERNATE_MATCH(6),
    CITY_EXACT_MATCH(9);

    private final int score;

    ScoringConstants(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
