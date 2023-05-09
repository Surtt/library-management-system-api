package com.rest_api.fs14backend.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
  @CreatedBy
  @JsonIgnore
  protected String createdBy;

  @CreatedDate
  @JsonIgnore
  protected Date createdDate;

  @LastModifiedBy
  @JsonIgnore
  protected String modifiedBy;

  @LastModifiedDate
  @JsonIgnore
  protected Date modifiedDate;
}
