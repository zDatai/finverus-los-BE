package com.zdatai.finverus.model.application;

import com.zdatai.finverus.model.AuditModifyUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "validation_rule")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ValidationRule extends AuditModifyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "validation_id")
    private Long validationId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "data_type", nullable = false)
    private String dataType;

    @Column(name = "regex_pattern")
    private String regexPattern;

    @Column(name = "default_message")
    private String defaultMessage;
}
