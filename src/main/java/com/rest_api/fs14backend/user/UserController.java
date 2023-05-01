package com.rest_api.fs14backend.user;

import com.rest_api.fs14backend.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable UUID id) {
        UserDTO user = userService.findById(id);

        if (user == null) {
            throw new NotFoundException("User not found");
        }
        return user;
    }

    @PostMapping
    public User createOne(@RequestBody User user) {
        return userService.createOne(user);
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable UUID id) {
        userService.deleteById(id);
    }

    @PutMapping("/{id}")
    public User updateOne(@RequestBody User user, @PathVariable UUID id) {
        if (user == null) {
            throw new NotFoundException("User not found");
        }

        user.setId(id);
        return userService.updateOne(user);
    }
}
