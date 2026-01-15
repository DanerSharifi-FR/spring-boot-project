package org.imt.tournamentmaster.configuration.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
public class UserBootstrapConfiguration {

    @Bean
    public CommandLineRunner initAdminUser(JdbcUserDetailsManager userDetailsManager,
                                           PasswordEncoder passwordEncoder,
                                           @Value("${app.security.admin.username}") String adminUsername,
                                           @Value("${app.security.admin.password}") String adminPassword) {
        return args -> {
            if (!userDetailsManager.userExists(adminUsername)) {
                userDetailsManager.createUser(User.withUsername(adminUsername)
                        .password(passwordEncoder.encode(adminPassword))
                        .roles("ADMIN")
                        .build());
            }
        };
    }
}
