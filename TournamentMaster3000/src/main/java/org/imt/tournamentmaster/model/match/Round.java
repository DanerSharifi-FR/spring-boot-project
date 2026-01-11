package org.imt.tournamentmaster.model.match;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;

import java.util.Objects;

@Entity
public class Round {

    @JsonIgnore
    @Id
    private long id;

    @Min(0)
    private int scoreA;

    @Min(0)
    private int scoreB;

    @Min(1)
    private int roundNumber;

    public Round() {
    }

    public Round(long id, int scoreA, int scoreB, int roundNumber) {
        this.id = id;
        this.scoreA = scoreA;
        this.scoreB = scoreB;
        this.roundNumber = roundNumber;
    }

    public long getId() {
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

    public void setId(long id) {
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

    public int determineWinner() {
        return scoreA - scoreB;
    }

    @Override
    public String toString() {
        return "Round{" +
                ", scoreA=" + scoreA +
                ", scoreB=" + scoreB +
                ", roundNumber=" + roundNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Round round = (Round) o;
        return id == round.id && scoreA == round.scoreA && scoreB == round.scoreB && roundNumber == round.roundNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, scoreA, scoreB, roundNumber);
    }
}
