package org.imt.tournamentmaster.controller.match;

import org.imt.tournamentmaster.model.match.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.imt.tournamentmaster.service.match.RoundService;

import java.util.List;

@RestController
@RequestMapping("/api/round")
public class RoundController {

    private final RoundService roundService;

    // TODO-04 : Apprécier qu'il n'y a également rien à faire ici comme nous passons par une couche service
    @Autowired
    public RoundController(RoundService roundService) {
        this.roundService = roundService;
    }

    // TODO-07 : Il faut gérer le nouveau retour Optional<Round> ici : s'il est vide, il faut renvoyer un code 404. Indice : Voir la classe ResponseEntity pour renvoyer un ResponseEntity<Round>.
    @GetMapping("/{id}")
    public Round getById(@PathVariable long id) {
        return roundService.getById(id);
    }

    @GetMapping
    public List<Round> getAll() {
        return roundService.getAll();
    }
}
