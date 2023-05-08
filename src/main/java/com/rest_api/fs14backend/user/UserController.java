package com.rest_api.fs14backend.user;

import com.rest_api.fs14backend.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @PostMapping("/signin")
  public String signIn(@RequestBody AuthRequest authRequest) {
    return userService.signIn(authRequest);
  }

  @PostMapping("/signup")
  public User signUp(@RequestBody User user) {
    return userService.signUp(user);
  }

  @GetMapping("api/v1/users")
  public List<UserDTO> findAll() {
    return userService.findAll();
  }

  @GetMapping("api/v1/users/{id}")
  public UserDTO findById(@PathVariable UUID id) {
    UserDTO user = userService.findById(id);
    if (user == null) {
      throw new NotFoundException("User with id " + id + " not found");
    }
    return user;
  }

  @PostMapping("api/v1/users")
  public User createOne(@RequestBody User user) {
    return userService.createOne(user);
  }

  @DeleteMapping("api/v1/users/{id}")
  public void deleteOne(@PathVariable UUID id) {
    UserDTO user = userService.findById(id);
    if (user == null) {
      throw new NotFoundException("User with id " + id + " not found");
    }
    userService.deleteById(id);
  }

  @PutMapping("api/v1/users/{id}")
  public User updateOne(@RequestBody User user, @PathVariable UUID id) {
    if (user == null) {
      throw new NotFoundException("User with id " + id + " not found");
    }
    return userService.updateOne(user, id);
  }

  @PutMapping("api/v1/users/{userId}/role/{roleId}")
  public User assignRoleToUser(@PathVariable UUID userId, @PathVariable UUID roleId) {
    return userService.assignRoleToUser(userId, roleId);
  }
}
