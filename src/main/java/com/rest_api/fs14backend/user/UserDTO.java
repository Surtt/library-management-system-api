package com.rest_api.fs14backend.user;

import java.util.UUID;

public record UserDTO(
        UUID id,
        String firstName,
        String lastName,
        String email
) {
}
