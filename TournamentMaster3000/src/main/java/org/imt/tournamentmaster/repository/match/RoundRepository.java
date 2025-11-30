package org.imt.tournamentmaster.repository.match;

import org.imt.tournamentmaster.model.match.Round;
import org.imt.tournamentmaster.repository.Repository;

// TODO-01 : Transformer cette interface en CrudRepository et l'injecter dans le contexte de beans Spring
public interface RoundRepository extends Repository<Round> {

    // TODO-11 BONUS : Créer une méthode (de votre choix) en utilisant le mécanisme JPA et exposer son résultat par API REST  https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
}
