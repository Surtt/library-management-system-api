package com.rest_api.fs14backend.role;

import com.rest_api.fs14backend.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/roles")
public class RoleController {
  private final RoleService roleService;

  @GetMapping
  public List<Role> findAll() {
    return roleService.findAll();
  }

  @GetMapping("/{id}")
  public Role findById(@PathVariable UUID id) {
    Role role = roleService.findById(id);
    if (role == null) {
      throw new NotFoundException("Role with id " + id + " not found");
    }
    return role;
  }

  @PostMapping
  public Role createOne(@RequestBody Role role) {
    return roleService.createOne(role);
  }

  @PutMapping("/{id}")
  public Role updateOne(@RequestBody Role newRole, @PathVariable UUID id) {
    Role role = roleService.findById(id);
    if (role == null) {
      throw new NotFoundException("Role with id " + id + " not found");
    }
    return roleService.updateOne(newRole, id);
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable UUID id) {
    Role role = roleService.findById(id);
    if (role == null) {
      throw new NotFoundException("Role with id " + id + " not found");
    }
    roleService.deleteById(id);
  }
}
