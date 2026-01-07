package org.imt.tournamentmaster.configuration.health;

import org.imt.tournamentmaster.repository.match.MatchRepository;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MatchHealthIndicator implements HealthIndicator {

    private final MatchRepository matchRepository;

    public MatchHealthIndicator(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public Health health() {
        long matchCount = matchRepository.count();
        if (matchCount == 0) {
            return Health.down()
                    .withDetail("error", "Aucun match en base de données")
                    .build();
        }
        return Health.up()
                .withDetail("matchCount", matchCount)
                .build();
    }
}