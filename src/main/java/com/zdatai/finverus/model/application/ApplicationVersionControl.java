package com.zdatai.finverus.model.application;

import com.zdatai.finverus.model.AuditCreateUser;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "app_version_control")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class ApplicationVersionControl extends AuditCreateUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_version_control_id", nullable = false, unique = true)
    private Long applicationVersionControlId;

    @Column(name = "version_control_id", nullable = false, columnDefinition = "BIGINT DEFAULT 1")
    private Long versionControlId;

}
