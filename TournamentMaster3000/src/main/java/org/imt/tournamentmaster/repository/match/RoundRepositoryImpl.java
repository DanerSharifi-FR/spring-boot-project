package org.imt.tournamentmaster.repository.match;

import org.imt.tournamentmaster.model.match.Round;
import org.imt.tournamentmaster.repository.MapRepository;
import org.springframework.stereotype.Component;

import java.util.Map;

// TODO-02 : Supprimer cette classe qui devient inutile
@Component
public class RoundRepositoryImpl extends MapRepository<Round> implements RoundRepository {

    @Override
    public Map<Long, Round> initData() {
        return Map.of(
                1L, new Round(1L, 21, 14, 1),
                2L, new Round(2L, 19, 21, 2),
                3L, new Round(3L, 21, 17, 3)
        );
    }
}
