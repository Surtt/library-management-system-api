package com.rest_api.fs14backend.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleService {

  @Autowired
  private RoleRepository roleRepository;

  public List<Role> findAll() {
    return roleRepository.findAll();
  }

  public Role findById(UUID id) {
    return roleRepository.findById(id).orElse(null);
  }

  public void deleteById(UUID id) {
    roleRepository.deleteById(id);
  }

  public Role createOne(Role role) {
    return roleRepository.save(role);
  }

  public Role updateOne(Role role, UUID id) {
    role.setId(id);
    return roleRepository.save(role);
  }
}
