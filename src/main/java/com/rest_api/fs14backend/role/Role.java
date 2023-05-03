package com.rest_api.fs14backend.role;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

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
}
