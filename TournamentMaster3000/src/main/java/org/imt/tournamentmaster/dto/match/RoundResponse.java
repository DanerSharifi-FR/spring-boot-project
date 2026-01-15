package org.imt.tournamentmaster.dto.match;

public class RoundResponse {

    private Long id;
    private int scoreA;
    private int scoreB;
    private int roundNumber;

    public RoundResponse() {
    }

    public Long getId() {
        return id;
    }

    public int getScoreA() {
        return scoreA;
    }

    public int getScoreB() {
        return scoreB;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setScoreA(int scoreA) {
        this.scoreA = scoreA;
    }

    public void setScoreB(int scoreB) {
        this.scoreB = scoreB;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }
}
