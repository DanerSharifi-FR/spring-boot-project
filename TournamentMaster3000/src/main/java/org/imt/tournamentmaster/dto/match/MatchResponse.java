package org.imt.tournamentmaster.dto.match;

import java.util.List;

import org.imt.tournamentmaster.model.match.Match;

public class MatchResponse {

    private Long id;
    private EquipeSummary equipeA;
    private EquipeSummary equipeB;
    private List<RoundSummary> rounds;
    private Match.Status status;
    private Long winnerEquipeId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public EquipeSummary getEquipeA() { return equipeA; }
    public void setEquipeA(EquipeSummary equipeA) { this.equipeA = equipeA; }

    public EquipeSummary getEquipeB() { return equipeB; }
    public void setEquipeB(EquipeSummary equipeB) { this.equipeB = equipeB; }

    public List<RoundSummary> getRounds() { return rounds; }
    public void setRounds(List<RoundSummary> rounds) { this.rounds = rounds; }

    public Match.Status getStatus() { return status; }
    public void setStatus(Match.Status status) { this.status = status; }

    public Long getWinnerEquipeId() { return winnerEquipeId; }
    public void setWinnerEquipeId(Long winnerEquipeId) { this.winnerEquipeId = winnerEquipeId; }

    public static class EquipeSummary {
        private Long id;
        private String nom;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getNom() { return nom; }
        public void setNom(String nom) { this.nom = nom; }
    }

    public static class RoundSummary {
        private Long id;
        private int scoreA;
        private int scoreB;
        private int roundNumber;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public int getScoreA() { return scoreA; }
        public void setScoreA(int scoreA) { this.scoreA = scoreA; }

        public int getScoreB() { return scoreB; }
        public void setScoreB(int scoreB) { this.scoreB = scoreB; }

        public int getRoundNumber() { return roundNumber; }
        public void setRoundNumber(int roundNumber) { this.roundNumber = roundNumber; }
    }
}
