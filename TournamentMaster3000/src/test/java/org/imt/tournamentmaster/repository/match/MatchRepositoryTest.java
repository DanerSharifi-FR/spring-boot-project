package org.imt.tournamentmaster.repository.match;

import org.imt.tournamentmaster.model.match.Match;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.StreamSupport;
import java.time.LocalDateTime;

@SpringBootTest
public class MatchRepositoryTest {

    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(MatchRepositoryTest.class);

    @Autowired
    private MatchRepository matchRepository;

    @Test
    public void testFindById() {
        // find a match
        Match match = matchRepository.findById(1L).get();

        // assert
        Assertions.assertNotNull(match);
        Assertions.assertEquals(1L, match.getId());

        Assertions.assertNotNull(match.getEquipeA());
        Assertions.assertEquals(1L, match.getEquipeA().getId());

        Assertions.assertNotNull(match.getEquipeB());
        Assertions.assertEquals(2L, match.getEquipeB().getId());

        Assertions.assertNotNull(match.getRounds());
        Assertions.assertEquals(3, match.getRounds().size());

        Assertions.assertEquals(Match.Status.TERMINE, match.getStatus());

        LocalDateTime matchDate = LocalDateTime.of(2026, 1, 1, 14, 0);
        Assertions.assertEquals(matchDate, match.getDate());

        Assertions.assertEquals(match.getEquipeA(), match.determineWinner());
    }

    @Test
    public void testFindAll() {
        // find all matchs
        List<Match> matchs = StreamSupport.stream(matchRepository.findAll().spliterator(), false).toList();

        // assert
        Assertions.assertNotNull(matchs);
        Assertions.assertEquals(2, matchs.size());
    }

}
