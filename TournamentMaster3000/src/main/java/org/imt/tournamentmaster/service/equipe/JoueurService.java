package org.imt.tournamentmaster.service.equipe;

import org.imt.tournamentmaster.dto.equipe.JoueurRequest;
import org.imt.tournamentmaster.dto.equipe.JoueurResponse;
import org.imt.tournamentmaster.model.equipe.Joueur;
import org.imt.tournamentmaster.repository.equipe.JoueurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class JoueurService {

    private final JoueurRepository joueurRepository;

    @Autowired
    public JoueurService(JoueurRepository joueurRepository) {
        this.joueurRepository = joueurRepository;
    }

    @Transactional(readOnly = true)
    public Optional<JoueurResponse> getById(long id) {
        return joueurRepository.findById(id).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public List<JoueurResponse> getAll() {
        return StreamSupport.stream(joueurRepository.findAll().spliterator(), false)
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public JoueurResponse create(JoueurRequest request) {
        validate(request, true);
        if (joueurRepository.existsById(request.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Joueur déjà existant");
        }

        Joueur joueur = new Joueur();
        joueur.setId(request.getId());
        joueur.setNom(request.getNom());
        joueur.setPrenom(request.getPrenom());
        joueur.setNumero(request.getNumero());

        return toResponse(joueurRepository.save(joueur));
    }

    @Transactional
    public Optional<JoueurResponse> update(long id, JoueurRequest request) {
        Joueur joueur = joueurRepository.findById(id).orElse(null);
        if (joueur == null) {
            return Optional.empty();
        }

        validate(request, false);
        joueur.setNom(request.getNom());
        joueur.setPrenom(request.getPrenom());
        joueur.setNumero(request.getNumero());

        return Optional.of(toResponse(joueurRepository.save(joueur)));
    }

    @Transactional
    public boolean delete(long id) {
        if (!joueurRepository.existsById(id)) {
            return false;
        }
        joueurRepository.deleteById(id);
        return true;
    }

    private void validate(JoueurRequest request, boolean requireId) {
        if (request == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Body JSON manquant");
        }
        if (requireId && request.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id est obligatoire");
        }
        if (request.getNom() == null || request.getNom().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "nom est obligatoire");
        }
        if (request.getPrenom() == null || request.getPrenom().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "prenom est obligatoire");
        }
        if (request.getNumero() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "numero est obligatoire");
        }
    }

    private JoueurResponse toResponse(Joueur joueur) {
        JoueurResponse response = new JoueurResponse();
        response.setId(joueur.getId());
        response.setNom(joueur.getNom());
        response.setPrenom(joueur.getPrenom());
        response.setNumero(joueur.getNumero());
        return response;
    }
}
