package com.rest_api.fs14backend.category;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity(name = "category")
@Table(name = "category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
  @Id
  @Column
  @UuidGenerator
  @GeneratedValue
  private UUID id;

  @Column(nullable = false, columnDefinition = "varchar(50)")
  private String name;
}
