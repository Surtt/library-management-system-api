package com.rest_api.fs14backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;

    public UserService(UserDTOMapper userDTOMapper) {
        this.userDTOMapper = userDTOMapper;
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userDTOMapper).collect(Collectors.toList());
    }

    public UserDTO findById(UUID id) {
        return userRepository.findById(id).map(userDTOMapper).orElse(null);
    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    public User createOne(User user) {
        return userRepository.save(user);
    }

    public User updateOne(User user) {
        return userRepository.save(user);
    }
}
