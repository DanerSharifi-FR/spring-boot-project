package org.imt.tournamentmaster.dto.equipe;

import java.util.List;

public class EquipeRequest {

    private Long id;
    private String nom;
    private List<Long> joueurIds;

    public EquipeRequest() {
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public List<Long> getJoueurIds() {
        return joueurIds;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setJoueurIds(List<Long> joueurIds) {
        this.joueurIds = joueurIds;
    }
}
