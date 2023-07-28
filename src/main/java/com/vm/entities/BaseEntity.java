package com.vm.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "createdBy")
    private String createdBy;

    @Column(name = "createdDate")
    @CreatedDate
    private Instant createdDate = Instant.now();

    @Column(name = "updatedBy")
    private String updatedBy;

    @Column(name = "updatedDate")
    @LastModifiedDate
    private Instant updatedDate = Instant.now();
}
