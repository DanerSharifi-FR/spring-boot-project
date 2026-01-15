package org.imt.tournamentmaster.controller.equipe;

import org.imt.tournamentmaster.dto.equipe.JoueurRequest;
import org.imt.tournamentmaster.dto.equipe.JoueurResponse;
import org.imt.tournamentmaster.service.equipe.JoueurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/joueur")
public class JoueurController {

    private final JoueurService joueurService;

    @Autowired
    public JoueurController(JoueurService joueurService) {
        this.joueurService = joueurService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<JoueurResponse> getJoueurById(@PathVariable long id) {
        return joueurService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<JoueurResponse> getAllJoueurs() {
        return joueurService.getAll();
    }

    @PostMapping
    public ResponseEntity<JoueurResponse> create(@RequestBody JoueurRequest request) {
        JoueurResponse created = joueurService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JoueurResponse> update(@PathVariable long id, @RequestBody JoueurRequest request) {
        return joueurService.update(id, request)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        return joueurService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
