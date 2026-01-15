package org.imt.tournamentmaster.controller.match;

import org.imt.tournamentmaster.dto.match.RoundRequest;
import org.imt.tournamentmaster.dto.match.RoundResponse;
import org.imt.tournamentmaster.service.match.RoundService;
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
@RequestMapping("/api/round")
public class RoundController {

    private final RoundService roundService;

    @Autowired
    public RoundController(RoundService roundService) {
        this.roundService = roundService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoundResponse> getById(@PathVariable long id) {
        return roundService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<RoundResponse> getAll() {
        return roundService.getAll();
    }

    @GetMapping("/max/{scoreA}")
    public List<RoundResponse> getMaxScore(@PathVariable int scoreA) {
        return roundService.getByScoreAGreaterThanEqual(scoreA);
    }

    @PostMapping
    public ResponseEntity<RoundResponse> create(@RequestBody RoundRequest request) {
        RoundResponse created = roundService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoundResponse> update(@PathVariable long id, @RequestBody RoundRequest request) {
        return roundService.update(id, request)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        return roundService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
