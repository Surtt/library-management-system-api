package com.rest_api.fs14backend.role;

import com.rest_api.fs14backend.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleService {

  @Autowired
  private RoleRepository roleRepository;

  public List<Role> findAll() {
    return roleRepository.findAll();
  }

  public Optional<Role> findById(UUID id) {
    Optional<Role> roleOptional = roleRepository.findById(id);
    if (roleOptional.isEmpty()) {
      throw new NotFoundException("Role not found");
    }
    return roleRepository.findById(id);
  }

  public void deleteById(UUID id) {
    Optional<Role> roleOptional = roleRepository.findById(id);
    if (roleOptional.isEmpty()) {
      throw new NotFoundException("Role not found");
    }
    roleRepository.deleteById(id);
  }

  public Role createOne(Role role) {
    return roleRepository.save(role);
  }

  public Role updateOne(Role newRole, UUID id) {
    Optional<Role> roleOptional = roleRepository.findById(id);
    if (roleOptional.isEmpty()) {
      throw new NotFoundException("Role not found");
    }

    return roleRepository.findById(id).map(role -> {
      role.setName(newRole.getName());
      return roleRepository.save(role);
    }).orElseGet(() -> roleRepository.save(newRole));
  }
}
