package org.imt.tournamentmaster.dto.match;

import java.util.List;

import org.imt.tournamentmaster.model.match.Match;

public class MatchRequest {

    private Long equipeAId;
    private Long equipeBId;
    private Match.Status status;
    private List<RoundRequest> rounds;

    public Long getEquipeAId() { return equipeAId; }
    public void setEquipeAId(Long equipeAId) { this.equipeAId = equipeAId; }

    public Long getEquipeBId() { return equipeBId; }
    public void setEquipeBId(Long equipeBId) { this.equipeBId = equipeBId; }

    public Match.Status getStatus() { return status; }
    public void setStatus(Match.Status status) { this.status = status; }

    public List<RoundRequest> getRounds() { return rounds; }
    public void setRounds(List<RoundRequest> rounds) { this.rounds = rounds; }

    public static class RoundRequest {
        private Integer scoreA;
        private Integer scoreB;
        private Integer roundNumber;

        public Integer getScoreA() { return scoreA; }
        public void setScoreA(Integer scoreA) { this.scoreA = scoreA; }

        public Integer getScoreB() { return scoreB; }
        public void setScoreB(Integer scoreB) { this.scoreB = scoreB; }

        public Integer getRoundNumber() { return roundNumber; }
        public void setRoundNumber(Integer roundNumber) { this.roundNumber = roundNumber; }
    }
}
