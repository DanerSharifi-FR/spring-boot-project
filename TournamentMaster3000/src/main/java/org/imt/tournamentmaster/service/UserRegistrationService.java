package org.imt.tournamentmaster.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserRegistrationService {

    private final JdbcUserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;
    private final String adminUsername;

    public UserRegistrationService(JdbcUserDetailsManager userDetailsManager,
                                   PasswordEncoder passwordEncoder,
                                   @Value("${app.security.admin.username}") String adminUsername) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
        this.adminUsername = adminUsername;
    }

    public void registerUser(String username, String password) {
        if (!StringUtils.hasText(username) || !StringUtils.hasText(password)) {
            throw new IllegalArgumentException("Username and password are required.");
        }

        if (adminUsername.equalsIgnoreCase(username)) {
            throw new IllegalArgumentException("The admin username is reserved.");
        }

        if (userDetailsManager.userExists(username)) {
            throw new IllegalArgumentException("Username already exists.");
        }

        UserDetails user = User.withUsername(username)
                .password(passwordEncoder.encode(password))
                .roles("USER")
                .build();

        userDetailsManager.createUser(user);
    }
}
