package com.rest_api.fs14backend.user;

import com.rest_api.fs14backend.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {
  private final UserService userService;

  @GetMapping
  public List<UserDTO> findAll() {
    return userService.findAll();
  }

  @GetMapping("/{id}")
  public UserDTO findById(@PathVariable UUID id) {
    UserDTO user = userService.findById(id);
    if (user == null) {
      throw new NotFoundException("User with id " + id + " not found");
    }
    return user;
  }

  @PostMapping
  public User createOne(@RequestBody User user) {
    return userService.createOne(user);
  }

  @DeleteMapping("/{id}")
  public void deleteOne(@PathVariable UUID id) {
    UserDTO user = userService.findById(id);
    if (user == null) {
      throw new NotFoundException("User with id " + id + " not found");
    }
    userService.deleteById(id);
  }

  @PutMapping("/{id}")
  public User updateOne(@RequestBody User user, @PathVariable UUID id) {
    if (user == null) {
      throw new NotFoundException("User with id " + id + " not found");
    }
    return userService.updateOne(user, id);
  }

  @PutMapping("{userId}/role/{roleId}")
  public User assignRoleToUser(@PathVariable UUID userId, @PathVariable UUID roleId) {
    return userService.assignRoleToUser(userId, roleId);
  }
}
