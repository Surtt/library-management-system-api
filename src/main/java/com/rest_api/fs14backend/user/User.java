package com.rest_api.fs14backend.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rest_api.fs14backend.checkout.Checkout;
import com.rest_api.fs14backend.role.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.*;


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

  @Column(unique = true, columnDefinition = "varchar(50)")
  private String email;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @Column(nullable = false)
  private String password;

  @ManyToMany
  @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Checkout> checkoutList = new ArrayList<>();

  public User(String firstName, String lastName, String email, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
  }

//  public List<String> getRoles() {
//    return roles.stream()
//            .map(Role::getName)
//            .collect(Collectors.toList());
//  }
}
