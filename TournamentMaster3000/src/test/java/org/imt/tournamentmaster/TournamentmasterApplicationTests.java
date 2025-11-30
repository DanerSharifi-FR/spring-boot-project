package org.imt.tournamentmaster;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// TODO-09 : Lancer tous les tests, regarder les logs pour comprendre qu'une base de données h2 est chargée en mémoire
// TODO-10 : Lancer la base de données via docker-compose, dans un terminal : `docker-compose up`. Lancer l'application, vérifier qu'elle fonctionne toujours et que les données sont cohérentes avec celles du fichier src/test/resources/import.sql
@SpringBootTest
class TournamentmasterApplicationTests {

  @Test
  void contextLoads() {
  }

}

