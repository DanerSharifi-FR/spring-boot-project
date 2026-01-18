package org.imt.tournamentmaster.model.match;

import java.util.List;
import java.util.Objects;
import java.time.LocalDateTime;

import org.imt.tournamentmaster.model.equipe.Equipe;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// Quand cette classe est transformée en entité, il faut spécifier le nom de la table en `match` en incluant les backquotes ` car match est un mot-clé réservé en SQL
@Entity
@Table(name = "`match`")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Equipe equipeA;

    @ManyToOne
    private Equipe equipeB;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
    name = "match_round",
    joinColumns = @JoinColumn(name = "match_id"),
    inverseJoinColumns = @JoinColumn(name = "round_id")
    )
    private List<Round> rounds; // Set est un type de collection, on va éviter les confusions et appeler ça un "round"

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    private LocalDateTime date;

    public Match() {
    }

    public Match(long id, Equipe equipeA, Equipe equipeB, List<Round> rounds, Status status, LocalDateTime date) {
        this.id = id;
        this.equipeA = equipeA;
        this.equipeB = equipeB;
        this.rounds = rounds;
        this.status = status;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public Equipe getEquipeA() {
        return equipeA;
    }

    public Equipe getEquipeB() {
        return equipeB;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEquipeA(Equipe equipeA) {
        this.equipeA = equipeA;
    }

    public void setEquipeB(Equipe equipeB) {
        this.equipeB = equipeB;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Equipe determineWinner() {
        int wonByA = 0;
        int wonByB = 0;

        for (Round round : rounds) {
            // on assume qu'une égalité est impossible
            if (round.determineWinner() > 0) {
                wonByA++;
            } else {
                wonByB++;
            }
        }

        if (wonByA > wonByB) {
            return equipeA;
        } else {
            return equipeB;
        }
    }

    @Override
    public String toString() {
        return "Match{" +
                "equipeA=" + equipeA +
                ", equipeB=" + equipeB +
                ", rounds=" + rounds +
                ", status=" + status +
                ", date=" + date +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return id == match.id && Objects.equals(equipeA, match.equipeA) && Objects.equals(equipeB, match.equipeB) && Objects.equals(rounds, match.rounds) && status == match.status && Objects.equals(date, match.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, equipeA, equipeB, rounds, status, date);
    }

    public enum Status {
        NOUVEAU, EN_COURS, TERMINE
    }
}
