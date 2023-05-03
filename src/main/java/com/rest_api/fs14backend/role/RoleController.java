package com.rest_api.fs14backend.role;

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
    return roleService.findById(id);
  }

  @PostMapping
  public Role createOne(@RequestBody Role role) {
    return roleService.createOne(role);
  }

  @PutMapping("/{id}")
  public Role updateOne(@RequestBody Role role, @PathVariable UUID id) {
    return roleService.updateOne(role, id);
  }

  @DeleteMapping("/{id}")
  public void deleteOne(@PathVariable UUID id) {
    roleService.deleteById(id);
  }
}
