package com.rest_api.fs14backend.user;

import com.rest_api.fs14backend.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class UserController {
  private final UserService userService;

  @PostMapping("/signin")
  public Map<String, String> signIn(@RequestBody AuthRequest authRequest) {
    return userService.signIn(authRequest);
  }

  @PostMapping("/signup")
  public User signUp(@RequestBody User user) {
    return userService.signUp(user);
  }

  @GetMapping("/users")
  public List<UserDTO> findAll() {
    return userService.findAll();
  }

  @GetMapping("/users/{id}")
  public User findById(@PathVariable UUID id) {
    User user = userService.findById(id);
    if (user == null) {
      throw new NotFoundException("User with id " + id + " not found");
    }
    return user;
  }

  @GetMapping("users/me")
  public User getMe(Principal principal) {
    return userService.getMe(principal.getName());
  }

  @PostMapping("/users")
  public User createOne(@RequestBody User user) {
    return userService.createOne(user);
  }

  @DeleteMapping("/users/{id}")
  public void deleteOne(@PathVariable UUID id) {
    User user = userService.findById(id);
    if (user == null) {
      throw new NotFoundException("User with id " + id + " not found");
    }
    userService.deleteById(id);
  }

  @PutMapping("/users/{id}")
  public User updateOne(@RequestBody User user, @PathVariable UUID id) {
    if (user == null) {
      throw new NotFoundException("User with id " + id + " not found");
    }
    return userService.updateOne(user, id);
  }

  @PutMapping("/users/{userId}/role/{roleId}")
  public User assignRoleToUser(@PathVariable UUID userId, @PathVariable UUID roleId) {
    return userService.assignRoleToUser(userId, roleId);
  }
}
