package org.imt.tournamentmaster.configuration.health;

import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.service.match.MatchService;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class MatchHealthIndicator implements HealthIndicator {

    private final MatchService matchService;

    public MatchHealthIndicator(MatchService matchService) {
        this.matchService = matchService;
    }

    @Override
    public Health health() {
        long matchCount = matchService.getAll().size();
        if (matchCount == 0) {
            return Health.down()
                    .withDetail("error", "No matches in database")
                    .build();
        }

        // check if no match played in the last month
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);
        List<Match> recentMatches = matchService.getMatchesAfter(oneMonthAgo);

        if (recentMatches.isEmpty()) {
            return Health.down()
                    .withDetail("error", "No matches played in the last month")
                    .withDetail("matchCount", matchCount)
                    .build();
        }

        return Health.up()
                .withDetail("matchCount", matchCount)
                .build();
    }
}