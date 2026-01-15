package org.imt.tournamentmaster.service.match;

import org.imt.tournamentmaster.dto.match.RoundRequest;
import org.imt.tournamentmaster.dto.match.RoundResponse;
import org.imt.tournamentmaster.model.match.Round;
import org.imt.tournamentmaster.repository.match.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class RoundService {

    private final RoundRepository roundRepository;

    @Autowired
    public RoundService(RoundRepository roundRepository) {
        this.roundRepository = roundRepository;
    }

    public Optional<RoundResponse> getById(long id) {
        return roundRepository.findById(id).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public List<RoundResponse> getAll() {
        return StreamSupport.stream(roundRepository.findAll().spliterator(), false)
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<RoundResponse> getByScoreAGreaterThanEqual(int scoreA) {
        return roundRepository.findByScoreAGreaterThanEqual(scoreA).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public RoundResponse create(RoundRequest request) {
        validate(request);
        Round round = new Round();
        round.setScoreA(request.getScoreA());
        round.setScoreB(request.getScoreB());
        round.setRoundNumber(request.getRoundNumber());
        return toResponse(roundRepository.save(round));
    }

    @Transactional
    public Optional<RoundResponse> update(long id, RoundRequest request) {
        Round round = roundRepository.findById(id).orElse(null);
        if (round == null) {
            return Optional.empty();
        }

        validate(request);
        round.setScoreA(request.getScoreA());
        round.setScoreB(request.getScoreB());
        round.setRoundNumber(request.getRoundNumber());
        return Optional.of(toResponse(roundRepository.save(round)));
    }

    @Transactional
    public boolean delete(long id) {
        if (!roundRepository.existsById(id)) {
            return false;
        }
        roundRepository.deleteById(id);
        return true;
    }

    private void validate(RoundRequest request) {
        if (request == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Body JSON manquant");
        }
        if (request.getScoreA() == null || request.getScoreB() == null || request.getRoundNumber() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "scoreA, scoreB et roundNumber sont obligatoires");
        }
    }

    private RoundResponse toResponse(Round round) {
        RoundResponse response = new RoundResponse();
        response.setId(round.getId());
        response.setScoreA(round.getScoreA());
        response.setScoreB(round.getScoreB());
        response.setRoundNumber(round.getRoundNumber());
        return response;
    }
}
