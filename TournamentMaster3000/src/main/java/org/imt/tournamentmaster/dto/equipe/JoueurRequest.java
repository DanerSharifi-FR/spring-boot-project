package org.imt.tournamentmaster.dto.equipe;

public class JoueurRequest {

    private Long id;
    private String nom;
    private String prenom;
    private Integer numero;

    public JoueurRequest() {
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
}
