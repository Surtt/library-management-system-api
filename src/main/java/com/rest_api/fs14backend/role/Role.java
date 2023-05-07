package com.rest_api.fs14backend.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rest_api.fs14backend.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;


@Entity(name = "role")
@Table(name = "role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
  @Id
  @Column
  @UuidGenerator
  @GeneratedValue
  private UUID id;
  @Column(nullable = false, unique = true, columnDefinition = "varchar(20)")
  private String name;

  @JsonIgnore
  @ManyToMany(mappedBy = "roles")
  private Set<User> users = new HashSet<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Role role = (Role) o;
    return id.equals(role.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
