package com.rest_api.fs14backend.user;

import com.rest_api.fs14backend.role.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity(name = "user")
@Table(name = "`user`")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @Column
  @UuidGenerator
  @GeneratedValue
  private UUID id;

  @Column(nullable = false, columnDefinition = "varchar(20)")
  private String firstName;

  @Column(nullable = false, columnDefinition = "varchar(20)")
  private String lastName;

  @Column(nullable = false, unique = true, columnDefinition = "varchar(50)")
  private String email;

  @Column(nullable = false, columnDefinition = "varchar(50)")
  private String password;

  @ManyToMany
  @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();
}
