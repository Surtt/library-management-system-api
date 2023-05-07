package com.rest_api.fs14backend.user;

import com.rest_api.fs14backend.role.Role;

import java.util.Set;
import java.util.UUID;

public record UserDTO(UUID id, String firstName, String lastName, String email, Set<Role> roles) {
}
