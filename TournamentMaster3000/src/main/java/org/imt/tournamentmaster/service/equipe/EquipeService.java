package org.imt.tournamentmaster.service.equipe;

import org.imt.tournamentmaster.dto.equipe.EquipeRequest;
import org.imt.tournamentmaster.dto.equipe.EquipeResponse;
import org.imt.tournamentmaster.dto.equipe.JoueurResponse;
import org.imt.tournamentmaster.model.equipe.Equipe;
import org.imt.tournamentmaster.model.equipe.Joueur;
import org.imt.tournamentmaster.repository.equipe.EquipeRepository;
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
public class EquipeService {

    private final EquipeRepository equipeRepository;
    private final JoueurRepository joueurRepository;

    @Autowired
    public EquipeService(EquipeRepository equipeRepository, JoueurRepository joueurRepository) {
        this.equipeRepository = equipeRepository;
        this.joueurRepository = joueurRepository;
    }

    @Transactional(readOnly = true)
    public Optional<EquipeResponse> getById(long id) {
        return equipeRepository.findById(id).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public List<EquipeResponse> getAll() {
        return StreamSupport.stream(equipeRepository.findAll().spliterator(), false)
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public EquipeResponse create(EquipeRequest request) {
        validate(request, true);
        if (equipeRepository.existsById(request.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Equipe déjà existante");
        }

        Equipe equipe = new Equipe();
        equipe.setId(request.getId());
        equipe.setNom(request.getNom());
        equipe.setJoueurs(loadJoueurs(request.getJoueurIds()));

        return toResponse(equipeRepository.save(equipe));
    }

    @Transactional
    public Optional<EquipeResponse> update(long id, EquipeRequest request) {
        Equipe equipe = equipeRepository.findById(id).orElse(null);
        if (equipe == null) {
            return Optional.empty();
        }

        validate(request, false);
        equipe.setNom(request.getNom());
        equipe.setJoueurs(loadJoueurs(request.getJoueurIds()));

        return Optional.of(toResponse(equipeRepository.save(equipe)));
    }

    @Transactional
    public boolean delete(long id) {
        if (!equipeRepository.existsById(id)) {
            return false;
        }
        equipeRepository.deleteById(id);
        return true;
    }

    private void validate(EquipeRequest request, boolean requireId) {
        if (request == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Body JSON manquant");
        }
        if (requireId && request.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id est obligatoire");
        }
        if (request.getNom() == null || request.getNom().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "nom est obligatoire");
        }
        if (request.getJoueurIds() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "joueurIds est obligatoire");
        }
    }

    private List<Joueur> loadJoueurs(List<Long> joueurIds) {
        return joueurIds.stream()
                .map(id -> joueurRepository.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Joueur introuvable: " + id)))
                .toList();
    }

    private EquipeResponse toResponse(Equipe equipe) {
        EquipeResponse response = new EquipeResponse();
        response.setId(equipe.getId());
        response.setNom(equipe.getNom());
        if (equipe.getJoueurs() != null) {
            response.setJoueurs(equipe.getJoueurs().stream().map(this::toJoueurResponse).toList());
        } else {
            response.setJoueurs(List.of());
        }
        return response;
    }

    private JoueurResponse toJoueurResponse(Joueur joueur) {
        JoueurResponse response = new JoueurResponse();
        response.setId(joueur.getId());
        response.setNom(joueur.getNom());
        response.setPrenom(joueur.getPrenom());
        response.setNumero(joueur.getNumero());
        return response;
    }
}
