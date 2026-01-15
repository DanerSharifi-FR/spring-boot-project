package org.imt.tournamentmaster.dto.equipe;

import java.util.List;

public class EquipeResponse {

    private Long id;
    private String nom;
    private List<JoueurResponse> joueurs;

    public EquipeResponse() {
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public List<JoueurResponse> getJoueurs() {
        return joueurs;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setJoueurs(List<JoueurResponse> joueurs) {
        this.joueurs = joueurs;
    }
}
