package org.imt.tournamentmaster.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegistrationRequest(
        @NotBlank(message = "Username is required.")
        @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters.")
        String username,
        @NotBlank(message = "Password is required.")
        @Size(min = 6, max = 72, message = "Password must be between 6 and 72 characters.")
        String password
) {
}
