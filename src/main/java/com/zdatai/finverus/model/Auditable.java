package com.zdatai.finverus.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable extends BaseEntity {

    @CreatedBy
    @Column(updatable = false, nullable = false)
    private String createdUser;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdDateTime;

    @LastModifiedBy
    @Column(nullable = false)
    private String modifiedUser;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedDateTime;
}
