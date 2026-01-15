package org.imt.tournamentmaster.dto.match;

public class RoundRequest {

    private Integer scoreA;
    private Integer scoreB;
    private Integer roundNumber;

    public RoundRequest() {
    }

    public Integer getScoreA() {
        return scoreA;
    }

    public Integer getScoreB() {
        return scoreB;
    }

    public Integer getRoundNumber() {
        return roundNumber;
    }

    public void setScoreA(Integer scoreA) {
        this.scoreA = scoreA;
    }

    public void setScoreB(Integer scoreB) {
        this.scoreB = scoreB;
    }

    public void setRoundNumber(Integer roundNumber) {
        this.roundNumber = roundNumber;
    }
}
