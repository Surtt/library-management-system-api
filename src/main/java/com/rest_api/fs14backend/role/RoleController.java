package com.rest_api.fs14backend.role;

import com.rest_api.fs14backend.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/roles")
public class RoleController {

  @Autowired
  private RoleService roleService;

  @GetMapping
  public List<Role> findAll() {
    return roleService.findAll();
  }

  @GetMapping("/{id}")
  public Optional<Role> findById(@PathVariable UUID id) {
    Optional<Role> role = roleService.findById(id);
    if (role.isEmpty()) {
      throw new NotFoundException("Role with id " + id + " not found");
    }
    return roleService.findById(id);
  }

  @PostMapping
  public Role createOne(@RequestBody Role role) {
    return roleService.createOne(role);
  }

  @PutMapping("/{id}")
  public Role updateOne(@RequestBody Role newRole, @PathVariable UUID id) {
    Optional<Role> role = roleService.findById(id);
    if (role.isEmpty()) {
      throw new NotFoundException("Role with id " + id + " not found");
    }
    return roleService.updateOne(newRole, id);
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable UUID id) {
    Optional<Role> role = roleService.findById(id);
    if (role.isEmpty()) {
      throw new NotFoundException("Role with id " + id + " not found");
    }
    roleService.deleteById(id);
  }
}
