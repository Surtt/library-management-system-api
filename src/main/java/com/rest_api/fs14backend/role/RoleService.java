package com.rest_api.fs14backend.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleService {
  private final RoleRepository roleRepository;

  public List<Role> findAll() {
    return roleRepository.findAll();
  }

  public Role findById(UUID id) {
    return roleRepository.findById(id)
            .orElse(null);
  }

  public void deleteById(UUID id) {
    roleRepository.deleteById(id);
  }

  public Role createOne(Role role) {
    return roleRepository.save(role);
  }

  public Role updateOne(Role newRole, UUID id) {
    return roleRepository.findById(id)
            .map(role -> {
              role.setName(newRole.getName());
              return roleRepository.save(role);
            })
            .orElseGet(() -> roleRepository.save(newRole));
  }
}
