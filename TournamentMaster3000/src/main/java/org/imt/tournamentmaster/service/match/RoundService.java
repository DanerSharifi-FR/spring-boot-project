package org.imt.tournamentmaster.service.match;

import org.imt.tournamentmaster.model.match.Round;
import org.imt.tournamentmaster.repository.match.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoundService {

    private final RoundRepository roundRepository;

    // TODO-03 : Apprécier qu'il n'y a rien à faire ici comme nous injectons déjà l'interface
    @Autowired
    public RoundService(RoundRepository roundRepository) {
        this.roundRepository = roundRepository;
    }

    // TODO-05 : Bien que le findById existe toujours, il retourne maintenant un type différent : Optional<Match> qui indique que le résultat peut être vide (= absence de donnée) => modifier le service pour que ce type remonte jusqu'au controller, c'est à lui d'adapter sa réponse
    public Round getById(long id) {
        return roundRepository.findById(id);
    }

    // TODO-06 : Pareillement, findAll retourne maintenant un type Iterable<Match> => ça n'intéresse pas le controller, il faut réaliser la conversion Iterable -> List ici
    public List<Round> getAll() {
        return roundRepository.findAll();
    }
}
