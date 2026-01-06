package org.imt.tournamentmaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class TournamentmasterApplication {

  public static void main(String[] args) {
    SpringApplication.run(TournamentmasterApplication.class, args);
  }

}

