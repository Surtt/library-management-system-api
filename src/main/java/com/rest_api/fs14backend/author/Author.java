package com.rest_api.fs14backend.author;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rest_api.fs14backend.base.BaseEntity;
import com.rest_api.fs14backend.book.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity(name = "author")
@Table(name = "author")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author extends BaseEntity {
  @Id
  @Column
  @UuidGenerator
  @GeneratedValue
  private UUID id;
  @Column(nullable = false, columnDefinition = "varchar(50)")
  private String name;

  @JsonIgnore
  @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
  private Set<Book> books = new HashSet<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Author author = (Author) o;
    return id.equals(author.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Author{" + "id=" + id + ", name='" + name + '\'' + ", books=" + books + ", createdBy='" + createdBy + '\'' + ", createdDate=" + createdDate + ", modifiedBy='" + modifiedBy + '\'' + ", modifiedDate=" + modifiedDate + '}';
  }
}
