package org.imt.tournamentmaster.service.match;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.imt.tournamentmaster.dto.match.MatchRequest;
import org.imt.tournamentmaster.dto.match.MatchResponse;
import org.imt.tournamentmaster.model.equipe.Equipe;
import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.model.match.Round;
import org.imt.tournamentmaster.repository.equipe.EquipeRepository;
import org.imt.tournamentmaster.repository.match.MatchRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final EquipeRepository equipeRepository;

    public MatchService(MatchRepository matchRepository, EquipeRepository equipeRepository) {
        this.matchRepository = matchRepository;
        this.equipeRepository = equipeRepository;
    }

    @Transactional(readOnly = true)
    public Optional<MatchResponse> getById(long id) {
        return matchRepository.findById(id).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public List<MatchResponse> getAll() {
        return matchRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional
    public MatchResponse create(MatchRequest req) {
        validate(req);

        Equipe equipeA = equipeRepository.findById(req.getEquipeAId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipe A introuvable"));
        Equipe equipeB = equipeRepository.findById(req.getEquipeBId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipe B introuvable"));

        Match match = new Match();
        match.setEquipeA(equipeA);
        match.setEquipeB(equipeB);
        match.setStatus(req.getStatus());
        match.setRounds(toRounds(req.getRounds()));

        Match saved = matchRepository.save(match);
        return toResponse(saved);
    }

    @Transactional
    public Optional<MatchResponse> update(long id, MatchRequest req) {
        Match match = matchRepository.findById(id).orElse(null);
        if (match == null) return Optional.empty();

        validate(req);

        Equipe equipeA = equipeRepository.findById(req.getEquipeAId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipe A introuvable"));
        Equipe equipeB = equipeRepository.findById(req.getEquipeBId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipe B introuvable"));

        match.setEquipeA(equipeA);
        match.setEquipeB(equipeB);
        match.setStatus(req.getStatus());
        List<Round> newRounds = toRounds(req.getRounds());

        if (match.getRounds() == null) {
            match.setRounds(new java.util.ArrayList<>());
        }
        match.getRounds().clear();
        match.getRounds().addAll(newRounds);

        Match saved = matchRepository.save(match);
        return Optional.of(toResponse(saved));
    }

    @Transactional
    public boolean delete(long id) {
        if (!matchRepository.existsById(id)) return false;
        matchRepository.deleteById(id);
        return true;
    }

    // ===== validation + mapping =====

    private void validate(MatchRequest req) {
        if (req == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Body JSON manquant");
        }
        if (req.getEquipeAId() == null || req.getEquipeBId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "equipeAId et equipeBId sont obligatoires");
        }
        if (Objects.equals(req.getEquipeAId(), req.getEquipeBId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "equipeAId et equipeBId doivent être différents");
        }
        if (req.getStatus() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "status est obligatoire");
        }
        if (req.getRounds() == null || req.getRounds().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "rounds doit contenir au moins 1 round");
        }

        // roundNumber uniques + pas d'égalité
        Set<Integer> seen = new HashSet<>();
        for (MatchRequest.RoundRequest r : req.getRounds()) {
            if (r == null || r.getScoreA() == null || r.getScoreB() == null || r.getRoundNumber() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Chaque round doit avoir scoreA, scoreB, roundNumber");
            }
            if (r.getScoreA().equals(r.getScoreB())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Égalité interdite dans un round (scoreA != scoreB)");
            }
            if (!seen.add(r.getRoundNumber())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "roundNumber doit être unique");
            }
        }
    }

    private List<Round> toRounds(List<MatchRequest.RoundRequest> rounds) {
    return rounds.stream()
            .map(r -> {
                Round round = new Round();
                round.setScoreA(r.getScoreA());
                round.setScoreB(r.getScoreB());
                round.setRoundNumber(r.getRoundNumber());
                return round;
            })
            .collect(java.util.stream.Collectors.toCollection(java.util.ArrayList::new));
    }


    private MatchResponse toResponse(Match match) {
        MatchResponse res = new MatchResponse();
        res.setId(match.getId());
        res.setStatus(match.getStatus());

        MatchResponse.EquipeSummary a = new MatchResponse.EquipeSummary();
        a.setId(match.getEquipeA() != null ? match.getEquipeA().getId() : null);
        a.setNom(match.getEquipeA() != null ? match.getEquipeA().getNom() : null);
        res.setEquipeA(a);

        MatchResponse.EquipeSummary b = new MatchResponse.EquipeSummary();
        b.setId(match.getEquipeB() != null ? match.getEquipeB().getId() : null);
        b.setNom(match.getEquipeB() != null ? match.getEquipeB().getNom() : null);
        res.setEquipeB(b);

        if (match.getRounds() != null) {
            res.setRounds(match.getRounds().stream().map(r -> {
                MatchResponse.RoundSummary rr = new MatchResponse.RoundSummary();
                rr.setId(r.getId());
                rr.setScoreA(r.getScoreA());
                rr.setScoreB(r.getScoreB());
                rr.setRoundNumber(r.getRoundNumber());
                return rr;
            }).toList());
        } else {
            res.setRounds(List.of());
        }

        // winner (si tu veux le montrer)
        if (match.getEquipeA() != null && match.getEquipeB() != null && match.getRounds() != null && !match.getRounds().isEmpty()) {
            res.setWinnerEquipeId(match.determineWinner().getId());
        }

        return res;
    }
}
