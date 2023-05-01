package com.rest_api.fs14backend.role;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;


@Entity(name = "role")
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
  @Id
  @Column
  @UuidGenerator
  @GeneratedValue
  private UUID id;
  @Column(nullable = false, unique = true, columnDefinition = "varchar(20)")
  private String roleName;

  @Override
  public String toString() {
    return "Role{" +
            "id=" + id +
            ", name='" + roleName + '\'' +
            '}';
  }
}
