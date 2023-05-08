package com.rest_api.fs14backend.user;

import com.rest_api.fs14backend.role.Role;
import com.rest_api.fs14backend.role.RoleRepository;
import com.rest_api.fs14backend.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

  private final UserDTOMapper userDTOMapper;
  //  private final UserDTOMapper userDTOMapper;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private JwtUtils jwtUtils;

  public UserService(UserDTOMapper userDTOMapper) {
    this.userDTOMapper = userDTOMapper;
  }

  public String signIn(AuthRequest authRequest) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

    User user = userRepository.findByEmail(authRequest.getEmail());

    return jwtUtils.generateToken(user);
  }

  public User signUp(User user) {
    User newUser = new User(user.getFirstName(), user.getLastName(), user.getEmail(),
            passwordEncoder.encode(user.getPassword()), user.getRoles());
    return userRepository.save(newUser);

  }

  public List<UserDTO> findAll() {
    return userRepository.findAll()
            .stream()
            .map(userDTOMapper)
            .collect(Collectors.toList());
  }

  public UserDTO findById(UUID id) {
    return userRepository.findById(id)
            .map(userDTOMapper)
            .orElse(null);
  }

  public void deleteById(UUID id) {
    userRepository.deleteById(id);
  }

  public User createOne(User user) {
    return userRepository.save(user);
  }

  public User updateOne(User newUser, UUID id) {
    return userRepository.findById(id)
            .map(user -> {
              user.setFirstName(newUser.getFirstName());
              user.setLastName(newUser.getLastName());
              user.setEmail(newUser.getEmail());
              user.setRoles(newUser.getRoles());
              return userRepository.save(user);
            })
            .orElseGet(() -> userRepository.save(newUser));
  }

  public User assignRoleToUser(UUID userId, UUID roleId) {
    Set<Role> roleSet = null;
    User user = userRepository.findById(userId)
            .get();
    Role role = roleRepository.findById(roleId)
            .get();
    roleSet = user.getRoles();
    roleSet.add(role);
    user.setRoles(roleSet);
    return userRepository.save(user);
  }
}
