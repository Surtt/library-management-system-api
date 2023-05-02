package com.rest_api.fs14backend.author;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import java.util.UUID;

@Entity(name = "author")
@Table(name = "author")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @Column
    @UuidGenerator
    @GeneratedValue
    private UUID id;
    @Column(nullable = false, columnDefinition = "varchar(50)")
    private String name;
}
